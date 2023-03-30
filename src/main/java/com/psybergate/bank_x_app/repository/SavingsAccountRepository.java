package com.psybergate.bank_x_app.repository;

import com.psybergate.bank_x_app.domain.account.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The repository class for storing and retrieving Savings Accounts to and from the database.
 */
@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

  /**
   * Retrieves all the Savings Accounts belonging to a specific Customer.
   *
   * @param customerNum The Customer's customerNum.
   * @return A list of SavingsAccount.
   */
  List<SavingsAccount> findByCustomerCustomerNumEquals(@Param("customerNum") Long customerNum);
}