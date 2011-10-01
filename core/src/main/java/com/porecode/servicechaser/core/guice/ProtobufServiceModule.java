package com.porecode.servicechaser.core.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.porecode.servicechaser.core.dao.ParameterValueDao;
import com.porecode.servicechaser.core.dao.impl.ParameterValueDaoImpl;
import com.porecode.servicechaser.core.protobuf.CoreServices;
import com.porecode.servicechaser.core.service.ParameterValueServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.inject.Inject;

/**
 * Configuration for protocol buffer services
 *
 * @author filipovskii_off
 */
public class ProtobufServiceModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(CoreServices.ParameterValueService.class).to(
        ParameterValueServiceImpl.class);
    bind(ParameterValueDao.class).to(ParameterValueDaoImpl.class);
  }

  @Inject
  @Provides
  Session getHibernateSession(SessionFactory factory) {
    // TODO: returns a valid session only for the first time
    return factory.getCurrentSession();
  }

  @Singleton
  @Provides
  SessionFactory getHibernateSessionFactory() {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      return new Configuration().configure().buildSessionFactory();
    } catch (Throwable ex) {
      // Make sure you log the exception, as it might be swallowed
      throw new ExceptionInInitializerError(ex);
    }
  }
}
