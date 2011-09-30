package com.porecode.servicechaser.core.dao;

import com.porecode.servicechaser.core.hibernate.ParameterValue;
import org.hibernate.Session;
import org.junit.Test;

import static org.easymock.EasyMock.*;

public class TestParameterValueDao {

  @Test
  public void testSelectAllEntities() throws Exception {
    Session session = createMock(Session.class);
    session.createCriteria(ParameterValue.class).list();
    replay(session);
    ParameterDao dao = new ParameterDao(session);
    dao.selectAll();
    verify(session);
  }
}
