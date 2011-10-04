package com.porecode.messaging.exception;

public final class ReceivingFailedException extends Exception {

  public ReceivingFailedException() {
  }

  public ReceivingFailedException(String s) {
    super(s);
  }

  public ReceivingFailedException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public ReceivingFailedException(Throwable throwable) {
    super(throwable);
  }
}
