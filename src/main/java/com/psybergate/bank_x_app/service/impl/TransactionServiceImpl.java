package com.psybergate.bank_x_app.service.impl;

import com.psybergate.bank_x_app.domain.Transaction.Transaction;
import com.psybergate.bank_x_app.exception.TransactionException;
import com.psybergate.bank_x_app.repository.TransactionRepository;
import com.psybergate.bank_x_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("transactionService")
@Transactional
@SuppressWarnings("unused")
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;

  @Autowired
  public TransactionServiceImpl(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  @Override
  public void create(Transaction transaction) {
    transactionRepository.save(transaction);
  }

  @Override
  public void update(Transaction transaction) {
    transactionRepository.save(transaction);
  }

  @Override
  public void delete(Long transactionNum) {
    Transaction transaction = findById(transactionNum);
    if (transaction == null) {
      throw new TransactionException("Transaction doesn't exist");
    }

    //TODO: Change to soft delete
    //Does a hard delete. Soft Delete is recommended where the data is kept but the user doesn't
    // have access to it.
    transactionRepository.delete(transaction);

  }

  @Override
  public Transaction findById(Long transactionNum) {
    Optional<Transaction> transaction = transactionRepository.findById(transactionNum);
    return transaction.orElse(null);
  }

  @Override
  public List<Transaction> findAllTransactions(Long accountNum) {
    return transactionRepository.findAllByAccountAccountNumEquals(accountNum);
  }
}