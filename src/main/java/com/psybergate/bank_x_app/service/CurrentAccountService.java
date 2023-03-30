package com.psybergate.bank_x_app.service;

import com.psybergate.bank_x_app.domain.account.CurrentAccount;

import java.util.List;

/**
 * The service class for handling business logic for Current Accounts.
 */
@SuppressWarnings({"UnnecessaryModifier", "unused"})
public interface CurrentAccountService {

  public CurrentAccount create(CurrentAccount currentAccount);

  public void update(CurrentAccount currentAccount);

  public void delete(Long accountNum);

  public CurrentAccount findById(Long accountNum);

  public List<CurrentAccount> findAll();

  /**
   * Retrieves all the Current Accounts belonging to a specific Customer.
   *
   * @param customerNum The Customer's customerNum.
   * @return A list of CurrentAccount.
   */
  public List<CurrentAccount> findByCustomerId(Long customerNum);
}