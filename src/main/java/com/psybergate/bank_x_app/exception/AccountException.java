package com.psybergate.bank_x_app.exception;

@SuppressWarnings("unused")
public class AccountException extends RuntimeException {

  public AccountException(String message) {
    super(message);
  }

  public AccountException(String message, Throwable cause) {
    super(message, cause);
  }
}