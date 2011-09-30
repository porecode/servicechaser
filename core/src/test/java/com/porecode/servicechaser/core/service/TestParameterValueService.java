package com.porecode.servicechaser.core.service;

import com.google.protobuf.RpcCallback;
import com.porecode.servicechaser.core.dao.ParameterValueDao;
import com.porecode.servicechaser.core.dao.impl.ParameterValueDaoImpl;
import com.porecode.servicechaser.core.hibernate.ParameterValue;
import com.porecode.servicechaser.core.protobuf.CoreServices;
import com.porecode.servicechaser.core.protobuf.EntityProtos;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.porecode.servicechaser.core.protobuf.CoreServices.ParameterValueService;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestParameterValueService {

  private final static Long intValue = 1L;
  private final static String strValue = "asdf";

  /**
   * Used for testing asynchronous callbacks
   */
  private CountDownLatch latch;

  private ParameterValueDao dao;
  private Session session;
  private List<ParameterValue> parameterValues =
      new ArrayList<ParameterValue>();

  private static void initParamValues(List<ParameterValue> list) {
    list.clear();
    list.add(new ParameterValue(intValue, intValue, strValue));
    list.add(new ParameterValue(intValue, intValue, ""));
    list.add(new ParameterValue(0L, 0L, strValue));
  }

  @Before
  public void setUp() {
    session = createMock(Session.class);
    dao = new ParameterValueDaoImpl(session);
    initParamValues(parameterValues);
    latch = new CountDownLatch(1);
  }

  @Test
  public void testGetAllParameterValuesService() throws Exception {

    dao = createMock(ParameterValueDao.class);
    expect(dao.selectAll()).andReturn(parameterValues);
    replay(dao);

    ParameterValueService service =
        new ParameterValueServiceImpl(dao);

    service.listAll(null, null,
        new RpcCallback<CoreServices.ParameterValueResponse>() {

          @Override
          public void run(
              CoreServices.ParameterValueResponse res) {
            for (int i = 0; i < parameterValues.size(); i++) {
              ParameterValue passed = parameterValues.get(i);
              EntityProtos.ParameterValue returned = res.getParameterValues(i);

              assertEquals(passed.getId(), (Long) returned.getId());
              assertEquals(passed.getIntValue(), (Long) returned.getIntValue());
              assertEquals(passed.getTextValue(), returned.getTextValue());
              verify(dao);
              latch.countDown();
            }
          }
        });
      if (!latch.await(2, TimeUnit.SECONDS)) {
        fail("Service didn't answer in 2 seconds");
      }
    }
}

