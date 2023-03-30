package com.psybergate.bank_x_app.domain.customer;

import com.psybergate.bank_x_app.domain.account.Account;
import com.psybergate.bank_x_app.domain.account.CurrentAccount;
import com.psybergate.bank_x_app.domain.account.SavingsAccount;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * A class to represent a user of the bank-x-app.
 */
@Entity
@Table(name = "customer")
@SuppressWarnings("unused")
public class Customer {

  /**
   * A unique identifier for the database. The database will generate the value.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "customer_num")
  private Long customerNum;

  /**
   * The customer's first name only.
   */
  @NotEmpty(message = "Customer firstname cannot be empty.")
  @Column(name = "first_name", nullable = false)
  private String firstName;

  /**
   * The customer's last name or surname.
   */
  @NotEmpty(message = "Customer lastname cannot be empty.")
  @Column(name = "lastname", nullable = false)
  private String LastName;

  /**
   * The customer's date of birth.
   */
  @Past
  @Column(name = "date_of_birth", nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateOfBirth;

  /**
   * The collection of all the customer's accounts.
   */
  @Transient
  private List<Account> accounts = new ArrayList<>();

  /**
   * The collection of all the customer's current accounts only.
   */
  @Transient
  private List<CurrentAccount> currentAccounts = new ArrayList<>();

  /**
   * The collection of all the customer's savings accounts only.
   */
  @Transient
  private List<SavingsAccount> savingsAccounts = new ArrayList<>();

  public void addCurrentAccount() {
    CurrentAccount currentAccount = new CurrentAccount();
    currentAccounts.add(currentAccount);
    accounts.add(currentAccount);
  }

  public void addCurrentAccount(CurrentAccount currentAccount) {
    currentAccounts.add(currentAccount);
    accounts.add(currentAccount);
  }

  public void addSavingsAccount() {
    SavingsAccount savingsAccount = new SavingsAccount();
    savingsAccounts.add(savingsAccount);
    accounts.add(savingsAccount);
  }

  public void addSavingsAccount(SavingsAccount savingsAccount) {
    savingsAccounts.add(savingsAccount);
    accounts.add(savingsAccount);
  }

  public Long getCustomerNum() {
    return customerNum;
  }

  public void setCustomerNum(Long customerNum) {
    this.customerNum = customerNum;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String lastName) {
    LastName = lastName;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }

  public List<CurrentAccount> getCurrentAccounts() {
    return currentAccounts;
  }

  public void setCurrentAccounts(List<CurrentAccount> currentAccounts) {
    this.currentAccounts = currentAccounts;
  }

  public List<SavingsAccount> getSavingsAccounts() {
    return savingsAccounts;
  }

  public void setSavingsAccounts(List<SavingsAccount> savingsAccounts) {
    this.savingsAccounts = savingsAccounts;
  }
}