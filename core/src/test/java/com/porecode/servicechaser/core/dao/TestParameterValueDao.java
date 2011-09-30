package com.porecode.servicechaser.core.dao;

import com.porecode.servicechaser.core.hibernate.ParameterValue;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.Test;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;

public class TestParameterValueDao {

  @Test
  public void testSelectAllEntities() throws Exception {
    Session session = createMock(Session.class);
    Criteria criteria = createMock(Criteria.class);
    expect(session.createCriteria(ParameterValue.class)).andReturn(criteria);
    expect(criteria.list()).andReturn(new ArrayList<ParameterValue>());

    replay(session);

    ParameterValueDao dao = new ParameterValueDao(session);
    dao.selectAll();

    verify(session);
  }
}
