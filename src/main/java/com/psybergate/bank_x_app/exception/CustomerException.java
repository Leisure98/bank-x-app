package com.psybergate.bank_x_app.exception;

@SuppressWarnings("unused")
public class CustomerException extends RuntimeException {

  public CustomerException(String message) {
    super(message);
  }

  public CustomerException(String message, Throwable cause) {
    super(message, cause);
  }
}