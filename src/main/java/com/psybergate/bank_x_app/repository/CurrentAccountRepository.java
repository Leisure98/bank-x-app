package com.psybergate.bank_x_app.repository;

import com.psybergate.bank_x_app.domain.account.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The repository class for storing and retrieving Current Accounts to and from the database.
 */
@Repository("currentAccountRepository")
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {

  /**
   * Retrieves all the Current Accounts belonging to a specific Customer.
   *
   * @param customerNum The Customer's customerNum.
   * @return A list of CurrentAccount.
   */
  List<CurrentAccount> findByCustomerCustomerNumEquals(@Param("customerNum") Long customerNum);
}