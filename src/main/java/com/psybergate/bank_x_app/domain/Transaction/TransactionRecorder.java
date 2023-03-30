package com.psybergate.bank_x_app.domain.Transaction;

import com.psybergate.bank_x_app.domain.account.Account;
import com.psybergate.bank_x_app.domain.money.Money;

import java.util.List;

import static com.psybergate.bank_x_app.domain.Transaction.TransactionType.*;

@SuppressWarnings("unused")
public class TransactionRecorder {

  private static Transaction getTransaction(TransactionType transactionType, Account account,
                                            Account destinationAccount, String amount,
                                            String message) {
    Transaction transaction = new Transaction();
    transaction.setTransactionType(transactionType);
    transaction.setAccount(account);
    transaction.setDestinationAccount(destinationAccount);
    transaction.setAmount(amount);
    transaction.setMessage(message);
    return transaction;
  }

  private static Transaction recordTransaction(List<Transaction> transactions,
                                               TransactionType transactionType,
                                               Account account,
                                               Account destinationAccount,
                                               String amount, String message) {
    Transaction transaction = getTransaction(transactionType, account, destinationAccount, amount, message);
    transactions.add(transaction);
    System.out.println(message);
    return transaction;
  }

  private static Transaction recordTransaction(List<Transaction> transactions,
                                               TransactionType transactionType,
                                               Account account, String amount, String message) {
    return recordTransaction(transactions, transactionType, account, null, amount, message);
  }

  public static Transaction recordDeposit(List<Transaction> transactions, Account account,
                                          String amount) {
    String message = "Deposit successful";
    return recordTransaction(transactions, DEPOSIT, account, amount, message);
  }

  public static Transaction recordWithdraw(List<Transaction> transactions, Account account,
                                           String amount) {
    String message = "Withdraw successful.";
    return recordTransaction(transactions, WITHDRAW, account, amount, message);
  }

  public static Transaction recordTransfer(List<Transaction> transactions, Account account,
                                           Account destinationAccount, String amount) {
    String message = "Transfer successful.";
    return recordTransaction(transactions, TRANSFER, account, destinationAccount, amount, message);
  }

  public static Transaction recordPayment(List<Transaction> transactions, Account account,
                                          Account destinationAccount, String amount,
                                          Money transactionFee) {
    String message = "Payment successful. Transaction fee: R" + transactionFee.getValue();
    return recordTransaction(transactions, PAYMENT, account, destinationAccount, amount, message);
  }

  public static Transaction recordFailure(List<Transaction> transactions, Account account,
                                          Account destinationAccount, String amount, String message) {
    return recordTransaction(transactions, PAYMENT, account, destinationAccount, amount, message);
  }
}