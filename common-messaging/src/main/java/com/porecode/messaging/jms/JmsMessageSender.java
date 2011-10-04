package com.porecode.messaging.jms;

import com.porecode.messaging.MessageSender;
import com.porecode.messaging.exception.SendingFailedException;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import java.io.Serializable;

/**
 * Message sender, that uses JMS<br/>
 * Depends on JMS {@link Session} and {@link MessageProducer}
 *
 * @see <a href='http://www.ibm.com/developerworks/java/tutorials/j-jms/j-jms-updated-pdf.pdf'>
 *   Nice JMS tutorial</a>
 */
public final class JmsMessageSender implements MessageSender {

  private MessageProducer producer;
  private Session session;

  @Inject
  public JmsMessageSender(Session session, MessageProducer producer) throws Exception {
    this.producer = producer;
    this.session = session;
  }

  @Override
  public void send(String message) throws SendingFailedException {
    try {
      producer.send(session.createTextMessage(message));
    } catch (JMSException e) {
      throw new SendingFailedException(e);
    }

  }

  @Override
  public void send(Serializable object) throws SendingFailedException {
    if (object instanceof String) {
      send((String) object);
      return;
    }
    try {
      producer.send(session.createObjectMessage(object));
    } catch (JMSException e) {
      throw new SendingFailedException(e);
    }
  }
}
