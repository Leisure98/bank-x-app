package com.psybergate.bank_x_app.service;

import com.psybergate.bank_x_app.domain.account.SavingsAccount;

import java.util.List;

/**
 * The service class for handling business logic for Savings Accounts.
 */
@SuppressWarnings({"UnnecessaryModifier", "unused"})
public interface SavingsAccountService {

  public SavingsAccount create(SavingsAccount savingsAccount);

  public void update(SavingsAccount savingsAccount);

  public void delete(Long accountNum);

  public SavingsAccount findById(Long accountNum);

  public List<SavingsAccount> findAll();

  /**
   * Retrieves all the Savings Accounts belonging to a specific Customer.
   *
   * @param customerNum The Customer's customerNum.
   * @return A list of SavingsAccount.
   */
  public List<SavingsAccount> findByCustomerId(Long customerNum);
}