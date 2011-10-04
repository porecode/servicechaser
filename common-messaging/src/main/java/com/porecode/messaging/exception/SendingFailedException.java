package com.porecode.messaging.exception;

public final class SendingFailedException extends Exception {

  public SendingFailedException() {

  }

  public SendingFailedException(String s) {
    super(s);
  }

  public SendingFailedException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public SendingFailedException(Throwable throwable) {
    super(throwable);
  }
}
