package com.porecode.messaging.jms;

import com.porecode.messaging.MessageReceiver;
import com.porecode.messaging.exception.ConnectionFailedException;
import com.porecode.messaging.exception.ReceivingFailedException;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

/**
 * Jms version of MessageReceiver
 *
 * @see <a href='http://www.ibm.com/developerworks/java/tutorials/j-jms/j-jms-updated-pdf.pdf'>
 *   Nice JMS tutorial</a>
 */
public final class JmsMessageReceiver implements MessageReceiver {

  private MessageConsumer consumer;

  @Inject
  public JmsMessageReceiver(MessageConsumer consumer) {
    this.consumer = consumer;
  }

  public Message receive() throws ReceivingFailedException {
    try {
      return consumer.receive();
    } catch (JMSException e) {
      throw new ReceivingFailedException(e);
    }
  }

  public void setMessageListener(MessageListener listener) throws ConnectionFailedException {
    try {
      consumer.setMessageListener(listener);
    } catch (JMSException e) {
      throw new ConnectionFailedException(e);
    }
  }
}
