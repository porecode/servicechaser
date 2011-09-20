package com.porecode.messaging;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.porecode.messaging.exception.SendingFailedException;
import com.porecode.messaging.jms.JmsMessageSender;
import org.junit.Before;
import org.junit.Test;

import javax.jms.*;
import java.io.Serializable;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestMessageSender {

  private MessageProducer producer;
  private Session session;

  private MessageSender sender;

  private class TestMessagingModule extends AbstractModule {

    @Override
    protected void configure() {
      bind(MessageSender.class).to(JmsMessageSender.class);
      bind(MessageProducer.class).toInstance(producer);
      bind(Session.class).toInstance(session);
    }
  }

  @Before
  public void setUp() throws Throwable {
    producer = createMock(MessageProducer.class);
    session = createMock(Session.class);

    Injector inj = Guice.createInjector(new TestMessagingModule());
    sender = inj.getInstance(MessageSender.class);
  }

  @Test
  public void testSendCallsProperMethods() throws Throwable {
    // expect
    expect(
        session.createTextMessage(anyObject(String.class)))
        .andReturn(null);
    expect(
        session.createObjectMessage(anyObject(Serializable.class)))
        .andReturn(null);
    replay(session);
    
    producer.send(anyObject(TextMessage.class));
    producer.send(anyObject(ObjectMessage.class));
    replay(producer);

    // act
    sender.send("Hayo!");
    sender.send(new Serializable() {});

    // assert
    verify(session);
    verify(producer);
  }

  @Test
  public void testSendExceptionHandle() throws Exception {
    JMSException e = new JMSException("bla bla!");
    producer.send(anyObject(TextMessage.class));
    expectLastCall().andThrow(e);
    producer.send(anyObject(ObjectMessage.class));
    expectLastCall().andThrow(e);
    replay(producer);
    resetToNice(session);
    replay(session);

    try {
      sender.send("Ummm!");
      fail();
    } catch (SendingFailedException ex) {
      assertEquals(e, ex.getCause());
    }

    try {
      sender.send(new Serializable() {
      });
      fail();
    } catch (SendingFailedException ex) {
      assertEquals(e, ex.getCause());
    }

    verify(producer);
  }
}
