package com.porecode.messaging.jms;

import com.porecode.messaging.MessageReceiver;
import com.porecode.messaging.exception.ReceivingFailedException;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;

/**
 * Jms version of MessageReceiver
 *
 * @see <a href='http://www.ibm.com/developerworks/java/tutorials/j-jms/j-jms-updated-pdf.pdf'>
 *   Nice JMS tutorial</a>
 */
public class JmsMessageReceiver implements MessageReceiver {

  private MessageConsumer consumer;

  @Inject
  public JmsMessageReceiver(MessageConsumer consumer) {
    this.consumer = consumer;
  }

  @Override
  public Message receive() throws ReceivingFailedException {
    try {
      return consumer.receive();
    } catch (JMSException e) {
      throw new ReceivingFailedException(e);
    }
  }
}
