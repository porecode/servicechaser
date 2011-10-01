package com.porecode.servicechaser.core.dao.impl;

import com.porecode.servicechaser.core.dao.ParameterValueDao;
import com.porecode.servicechaser.core.hibernate.ParameterValue;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.inject.Inject;
import java.util.List;

/**
 * @author filipovskii_off
 */
public final class ParameterValueDaoImpl implements ParameterValueDao {

  private Session session;

  @Inject
  public ParameterValueDaoImpl(Session session) {
    this.session = session;
  }

  private ParameterValueDaoImpl() {

  }

  public List<ParameterValue> selectAll() {
    Transaction trans = session.beginTransaction();
    List<ParameterValue> result =
        session.createCriteria(ParameterValue.class).list();
    trans.commit();
    return result;
  }
}
