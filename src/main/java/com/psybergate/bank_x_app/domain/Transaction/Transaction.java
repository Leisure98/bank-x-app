package com.psybergate.bank_x_app.domain.Transaction;

import com.psybergate.bank_x_app.domain.account.Account;
import com.psybergate.bank_x_app.domain.account.CurrentAccount;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

import static com.psybergate.bank_x_app.domain.Transaction.TransactionRecorder.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SuppressWarnings("unused")
public class Transaction {

  /**
   * A unique identifier for the database. The database will generate the value.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "transaction_num")
  private Long transactionNum;

  /**
   * The unique identifier for the account which the transaction belongs to.
   */
  @NotNull
  @ManyToOne
  @JoinColumn(name = "account_num")
  private Account account;

  @Transient
  private TransactionType transactionType;

  /**
   * The account which the transaction was targeted at.
   */
  @ManyToOne
  @JoinColumn(name = "destination_account")
  private Account destinationAccount;

  /**
   * The amount of the transaction.
   */
  @Column(name = "amount")
  private String amount;

  /**
   * The message stating the result of the transaction.
   */
  @Column(name = "message")
  private String message;

  /**
   * The date of the transaction.
   */
  @Column(name = "date", nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate date;

  public void processTransaction() {
    Account account = this.account;
    Account destinationAccount = this.destinationAccount;
    String amount = this.amount;

    switch (this.transactionType) {
      case TRANSFER:
        account.moveMoney(destinationAccount, amount);
        recordTransfer(account.getTransactions(), account, destinationAccount, amount);
        break;
      case PAYMENT:
        if (account instanceof CurrentAccount) {
          CurrentAccount currentAccount = (CurrentAccount) account;
          currentAccount.makePayment(destinationAccount, amount);
          recordPayment(account.getTransactions(), account, destinationAccount, amount, currentAccount.calcTransactionFee(amount));
        } else {
          recordFailure(account.getTransactions(), account, destinationAccount, amount, "Account cannot make payments.");
        }
        break;
      case DEPOSIT:
        account.deposit(amount);
        recordDeposit(account.getTransactions(), account, amount);
        break;
      case WITHDRAW:
        account.withdraw(amount);
        recordWithdraw(account.getTransactions(), account, amount);
        break;
      default:
        recordFailure(account.getTransactions(), account, destinationAccount, amount, "Cannot " +
            "process transaction.");
        break;
    }
  }

  public Long getTransactionNum() {
    return transactionNum;
  }

  public void setTransactionNum(Long transactionNum) {
    this.transactionNum = transactionNum;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  public Account getDestinationAccount() {
    return destinationAccount;
  }

  public void setDestinationAccount(Account destinationAccount) {
    this.destinationAccount = destinationAccount;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}