package com.porecode.messaging.exception;

public class ConnectionFailedException extends Exception {

  public ConnectionFailedException() {
  }

  public ConnectionFailedException(String s) {
    super(s);
  }

  public ConnectionFailedException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public ConnectionFailedException(Throwable throwable) {
    super(throwable);
  }
}
