package com.psybergate.bank_x_app.exception;

@SuppressWarnings("unused")
public class TransactionException extends RuntimeException {

  public TransactionException(String message) {
    super(message);
  }

  public TransactionException(String message, Throwable cause) {
    super(message, cause);
  }
}