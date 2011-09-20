package com.porecode.messaging;

import com.porecode.messaging.exception.ConnectionFailedException;
import com.porecode.messaging.exception.ReceivingFailedException;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message receiver interface
 */
public interface MessageReceiver {

  /**
   * Receive message. Should block until one is available
   * @return
   */
  Message receive() throws ReceivingFailedException;

  void setMessageListener(MessageListener listener) throws ConnectionFailedException;
}
