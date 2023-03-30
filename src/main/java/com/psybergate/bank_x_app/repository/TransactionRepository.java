package com.psybergate.bank_x_app.repository;

import com.psybergate.bank_x_app.domain.Transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The repository class for storing and retrieving Transactions to and from the database.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  /**
   * Retrieves all the Transactions belonging to a specific Account.
   *
   * @param accountNum The Transaction's accountNum.
   * @return A list of Transactions.
   */
  List<Transaction> findAllByAccountAccountNumEquals(@Param("accountNum") Long accountNum);
}