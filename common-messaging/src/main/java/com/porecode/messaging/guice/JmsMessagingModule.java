package com.porecode.messaging.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.porecode.messaging.exception.ConnectionFailedException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jms.*;
import javax.naming.InitialContext;

public class JmsMessagingModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(InitialContext.class);
  }

  @Inject
  @Provides
  @Singleton
  Connection provideConnection(InitialContext initCtx) throws ConnectionFailedException {
    try {
      QueueConnectionFactory qcf = (QueueConnectionFactory) initCtx.lookup("ConnectionFactory");
      return qcf.createQueueConnection();
    } catch (Exception e) {
      throw new ConnectionFailedException(e);
    }
  }

  @Inject
  @Provides
  @Singleton
  Session provideSession(Connection connection) throws ConnectionFailedException {
    try {
      return connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
    } catch (Exception e) {
      throw new ConnectionFailedException(e);
    }
  }

  @Inject
  @Provides
  @Singleton
  Queue provideQueue(Session session) throws ConnectionFailedException {
    try {
       // todo: add binding constant
      return session.createQueue("ExpiryQueue");
    } catch (JMSException e) {
      throw new ConnectionFailedException(e);
    }
  }

  @Inject
  @Provides
  MessageProducer provideProducer(Session session, Queue queue) throws ConnectionFailedException {
    try {
      return session.createProducer(queue);
    } catch (JMSException e) {
      throw new ConnectionFailedException(e);
    }
  }

  @Inject
  @Provides
  MessageConsumer provideConsumer(Session session, Queue queue) throws ConnectionFailedException {
    try {
      return session.createConsumer(queue);
    } catch (JMSException e) {
      throw new ConnectionFailedException(e);
    }
  }
}
