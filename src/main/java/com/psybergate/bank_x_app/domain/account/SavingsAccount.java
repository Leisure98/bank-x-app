package com.psybergate.bank_x_app.domain.account;

import com.psybergate.bank_x_app.domain.money.Money;

import javax.persistence.Entity;
import javax.persistence.Table;

import static com.psybergate.bank_x_app.domain.Transaction.TransactionRecorder.recordDeposit;

/**
 * The Account representation of a Savings Account.
 */
@Entity
@Table(name = "savings_account")
@SuppressWarnings("unused")
public class SavingsAccount extends Account {

  private static final String SAVINGS_INTEREST_RATE = "0.005";

  public static SavingsAccount newCustomer() {
    SavingsAccount savingsAccount = new SavingsAccount();
    savingsAccount.setBalance(Money.JOINING_BONUS);
    return savingsAccount;
  }

  @Override
  public void deposit(String amount) {
    Money currentBalance = this.getBalance().multiply(SAVINGS_INTEREST_RATE);
    Money newBalance = currentBalance.add(amount);
    this.setBalance(newBalance);
    this.getTransactions().add(recordDeposit(this.getTransactions(), this, amount));
  }
}