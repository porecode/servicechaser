package com.porecode.servicechaser.core.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.porecode.rpc.protobuf.CoreServices;
import com.porecode.servicechaser.core.dao.ParameterValueDao;
import com.porecode.servicechaser.core.dao.impl.ParameterValueDaoImpl;
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

  @Singleton
  @Provides
  @SuppressWarnings("deprecated")
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
