package com.porecode.servicechaser.core.dao;

import com.porecode.servicechaser.core.hibernate.ParameterValue;
import org.hibernate.Session;

import java.util.List;

public final class ParameterValueDao {

  private Session session;

  private ParameterValueDao() {

  }

  public ParameterValueDao(Session session) {
    this.session = session;
  }
  
  public List<ParameterValue> selectAll() {
    return session.createCriteria(ParameterValue.class).list();
  }
}
