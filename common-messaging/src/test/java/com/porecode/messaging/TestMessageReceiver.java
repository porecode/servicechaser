package com.porecode.messaging;

import com.porecode.messaging.exception.ReceivingFailedException;
import com.porecode.messaging.jms.JmsMessageReceiver;
import org.junit.Before;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class TestMessageReceiver {

  private MessageConsumer consumer;
  private MessageReceiver receiver;

  @Before
  public void setUp() throws Exception {
    consumer = createMock(MessageConsumer.class);
    receiver = new JmsMessageReceiver(consumer);
  }

  @Test
  public void testReceiveDelegatesToConsumer() throws Exception {
    expect(consumer.receive()).andReturn(null);
    replay(consumer);

    receiver.receive();

    verify(consumer);
  }

  @Test
  public void testExceptionHandle() throws Exception {
    JMSException e = new JMSException("");
    expect(consumer.receive()).andThrow(e);
    replay(consumer);

    try {
      receiver.receive();
      fail();
    } catch (ReceivingFailedException ex) {
      assertEquals(e, ex.getCause());
    }
  }
}
