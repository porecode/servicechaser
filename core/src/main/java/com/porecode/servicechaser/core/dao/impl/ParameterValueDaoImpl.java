package com.porecode.servicechaser.core.dao.impl;

import com.porecode.servicechaser.core.dao.ParameterValueDao;
import com.porecode.servicechaser.core.hibernate.ParameterValue;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.List;

public final class ParameterValueDaoImpl implements ParameterValueDao {

  private Session session;

  @Inject
  public ParameterValueDaoImpl(Session session) {
    this.session = session;
  }

  private ParameterValueDaoImpl() {

  }

  public List<ParameterValue> selectAll() {
    return session.createCriteria(ParameterValue.class).list();
  }
}
