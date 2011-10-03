package com.porecode.servicechaser.core.dao.impl;

import com.porecode.servicechaser.core.dao.ParameterValueDao;
import com.porecode.servicechaser.core.hibernate.ParameterValue;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.inject.Inject;
import java.util.List;

/**
 * @author filipovskii_off
 */
public final class ParameterValueDaoImpl implements ParameterValueDao {

  private SessionFactory sessionFactory;

  @Inject
  public ParameterValueDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  private ParameterValueDaoImpl() {

  }

  public List<ParameterValue> selectAll() {
    Session session = sessionFactory.getCurrentSession();
    Transaction trans = session.beginTransaction();
    try {
          return session.createCriteria(ParameterValue.class).list();
    } finally {
      trans.commit();
    }
  }
}
