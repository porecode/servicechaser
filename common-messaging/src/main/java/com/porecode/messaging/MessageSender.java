package com.porecode.messaging;

import com.porecode.messaging.exception.SendingFailedException;

import java.io.Serializable;

/**
 * Interface of message sender<br/>
 * Implemented by {@link com.porecode.messaging.jms.JmsMessageSender}
 */
public interface MessageSender {

  void send(String message) throws SendingFailedException;
  void send(Serializable object) throws SendingFailedException;
}
