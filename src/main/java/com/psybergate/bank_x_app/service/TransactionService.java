package com.psybergate.bank_x_app.service;

import com.psybergate.bank_x_app.domain.Transaction.Transaction;

import java.util.List;

@SuppressWarnings({"UnnecessaryModifier", "unused"})
public interface TransactionService {

  public void create(Transaction transaction);

  void update(Transaction transaction);

  public void delete(Long transactionNum);

  public Transaction findById(Long transactionNum);

  /**
   * Retrieves all the Transactions belonging to a specific Account.
   *
   * @param accountNum The Transaction's accountNum.
   * @return A list of Transactions.
   */
  public List<Transaction> findAllTransactions(Long accountNum);

}
