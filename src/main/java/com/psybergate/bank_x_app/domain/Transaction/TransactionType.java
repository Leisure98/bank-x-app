package com.psybergate.bank_x_app.domain.Transaction;

@SuppressWarnings("unused")
public enum TransactionType {
  TRANSFER("Transfer"), PAYMENT("Payment"), WITHDRAW("Withdraw"), DEPOSIT("Deposit"), FAILURE(
      "Failed transaction");

  private final String transactionType;

  TransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public String getTransactionType() {
    return transactionType;
  }
}
