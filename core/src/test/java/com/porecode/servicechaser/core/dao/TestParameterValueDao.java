package com.porecode.servicechaser.core.dao;

import com.porecode.servicechaser.core.dao.impl.ParameterValueDaoImpl;
import com.porecode.servicechaser.core.hibernate.ParameterValue;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;

/**
 * @author filipovskii_off
 */
public class TestParameterValueDao {

  @Test
  public void testSelectAllEntities() throws Exception {
    SessionFactory sessionFactory = createMock(SessionFactory.class);
    Session session  = createMock(Session.class);
    Criteria criteria = createMock(Criteria.class);
    Transaction trans = createMock(Transaction.class);

    expect(sessionFactory.getCurrentSession()).andReturn(session);
    expect(session.beginTransaction()).andReturn(trans);
    expect(session.createCriteria(ParameterValue.class)).andReturn(criteria);
    expect(criteria.list()).andReturn(new ArrayList<ParameterValue>());
    trans.commit();

    replay(sessionFactory);
    replay(session);
    replay(criteria);
    replay(trans);

    ParameterValueDaoImpl dao = new ParameterValueDaoImpl(sessionFactory);
    dao.selectAll();

    verify(sessionFactory);
    verify(session);
    verify(criteria);
    verify(trans);
  }
}
