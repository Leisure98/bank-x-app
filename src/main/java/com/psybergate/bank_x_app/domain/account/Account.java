package com.psybergate.bank_x_app.domain.account;

import com.psybergate.bank_x_app.domain.customer.Customer;
import com.psybergate.bank_x_app.domain.money.Money;
import com.psybergate.bank_x_app.domain.Transaction.Transaction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.psybergate.bank_x_app.domain.Transaction.TransactionRecorder.*;

/**
 * The abstract class that all account classes inherit from.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"customer_num"})})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuppressWarnings("unused")
public abstract class Account {

  public static final double TRANSACTION_FEE_RATE = 0.0005;

  private static final String SAVINGS_INTEREST_RATE = "0.005";

  /**
   * A unique identifier for the database. The database will generate the value.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "account_num")
  private Long accountNum;

  /**
   * The unique identifier for the customer which the account belongs to.
   */
  @NotNull
  @ManyToOne
  @JoinColumn(name = "customer_num")
  private Customer customer;

  /**
   * The balance of the account.
   */
  @Column(name = "balance")
  @Type(type = "com.psybergate.bank_x_app.domain.money.MoneyDecimalType")
  private Money balance = Money.ZERO;

  /**
   * The collection of transactions for the account.
   */
  @Transient
  private List<Transaction> transactions = new ArrayList<>();

  public abstract void deposit(String amount);

  private Money addSavingsInterest(Account account, String amount) {
    Money currentBalance = account.getBalance().multiply(SAVINGS_INTEREST_RATE);
    return currentBalance.add(amount);
  }

  public void setDestinationAccountBalance(Account destinationAccount, String amount) {
    if (destinationAccount instanceof SavingsAccount) {
      destinationAccount.setBalance(addSavingsInterest(destinationAccount, amount));
    } else {
      destinationAccount.setBalance(destinationAccount.getBalance().add(amount));
    }
  }

  public void moveMoney(Account destinationAccount, String amount) {
    List<Account> accounts = customer.getAccounts();
    if (accounts.contains(this) && accounts.contains(destinationAccount)) {
      this.transferMoney(destinationAccount, amount);
    } else {
      System.out.println("You cannot move money between accounts not owned." +
          "\nPlease look at payments.");
    }
  }

  private void transferMoney(Account destinationAccount, String amount) {
    Money newBalance = this.getBalance().subtract(amount);

    if (newBalance.moreThan("0")) {
      this.setBalance(newBalance);
      this.setDestinationAccountBalance(destinationAccount, amount);
      this.getTransactions().add(recordTransfer(this.transactions, this, destinationAccount,
          amount));
    } else {
      String message = "Insufficient Funds.";
      this.getTransactions().add(recordFailure(this.transactions, this, destinationAccount,
          amount, message));
    }
  }

  public void withdraw(String amount) {
    Money newBalance = this.getBalance().subtract(amount);
    if (newBalance.moreThan("0")) {
      this.setBalance(newBalance);
      this.getTransactions().add(recordWithdraw(this.transactions, this, amount));
    } else {
      String message = "Insufficient Funds.";
      this.getTransactions().add(recordFailure(this.transactions, this, null, amount, message));
    }
  }

  public void processTransactions(List<Transaction> transactions) {
    for (Transaction transaction : transactions) {
      transaction.processTransaction();
    }

    System.out.println("All possible Transactions have been processed.");
  }

  public Long getAccountNum() {
    return accountNum;
  }

  public void setAccountNum(Long accountNum) {
    this.accountNum = accountNum;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Money getBalance() {
    return balance;
  }

  public void setBalance(Money balance) {
    this.balance = balance;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }
}