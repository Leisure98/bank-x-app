package com.psybergate.bank_x_app.domain.account;

import com.psybergate.bank_x_app.domain.Transaction.Transaction;
import com.psybergate.bank_x_app.domain.customer.Customer;
import com.psybergate.bank_x_app.domain.money.Money;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.List;

import static com.psybergate.bank_x_app.domain.Transaction.TransactionRecorder.*;

/**
 * The Account representation of a Current Account.
 */
@Entity
@Table(name = "current_account")
@SuppressWarnings("unused")
public class CurrentAccount extends Account {

  public void makePayment(Account destinationAccount, String amount) {
    Money newBalance = this.getBalance().subtract(amount);
    Money transactionFee = this.calcTransactionFee(amount);

    if (newBalance.moreThan("0")) {
      this.setBalance(newBalance.subtract(transactionFee));
      this.setDestinationAccountBalance(destinationAccount, amount);
      this.getTransactions().add(recordPayment(this.getTransactions(), this, destinationAccount,
          amount, transactionFee));
    } else {
      String message = "Insufficient Funds.";
      this.getTransactions().add(recordFailure(this.getTransactions(), this, destinationAccount,
          amount, message));
    }
  }

  public Money calcTransactionFee(String amount) {
    return new Money((Double.parseDouble(amount) * TRANSACTION_FEE_RATE));
  }

  @Override
  public void deposit(String amount) {
    Money newBalance = this.getBalance().add(amount);
    this.setBalance(newBalance);
    this.getTransactions().add(recordDeposit(this.getTransactions(), this, amount));
  }
}