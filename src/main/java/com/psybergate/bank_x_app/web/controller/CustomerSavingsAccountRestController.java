package com.psybergate.bank_x_app.web.controller;

import com.psybergate.bank_x_app.domain.Transaction.Transaction;
import com.psybergate.bank_x_app.domain.account.SavingsAccount;
import com.psybergate.bank_x_app.service.SavingsAccountService;
import com.psybergate.bank_x_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/customers/{id}/savings-accounts")
@SuppressWarnings("unused")
public class CustomerSavingsAccountRestController {

  private final SavingsAccountService savingsAccountService;

  private final TransactionService transactionService;

  @Autowired
  public CustomerSavingsAccountRestController(SavingsAccountService savingsAccountService,
                                              TransactionService transactionService) {
    this.savingsAccountService = savingsAccountService;
    this.transactionService = transactionService;
  }

  @GetMapping
  public List<SavingsAccount> currentAccountsForCustomer(@PathVariable("id") Long id) {
    return savingsAccountService.findByCustomerId(id);
  }

  @GetMapping("/{saId}")
  public SavingsAccount currentAccountById(@PathVariable("saId") Long id) {
    SavingsAccount savingsAccount = savingsAccountService.findById(id);

    if (savingsAccount != null) {
      List<Transaction> transactions =
          transactionService.findAllTransactions(savingsAccount.getAccountNum());
      transactions.sort(Comparator.comparing(Transaction::getDate));
      savingsAccount.setTransactions(transactions);
    }

    return savingsAccount;
  }

  @PostMapping
  public SavingsAccount createCurrentAccount(@Valid @RequestBody SavingsAccount savingsAccount) {
    return savingsAccountService.create(savingsAccount);
  }

  @PostMapping("/{saId}/events")
  public void createEvent(@PathVariable("saId") Long id, @RequestBody Transaction transaction) {
    SavingsAccount savingsAccount = savingsAccountService.findById(id);
    transaction.setAccount(savingsAccount);
    transactionService.create(transaction);
  }

  @PutMapping("/{saId}/update")
  public void updateCurrentAccount(@Valid @RequestBody SavingsAccount savingsAccount,
                                   @PathVariable("saId") Long id) {
    savingsAccountService.update(savingsAccount);
  }

  @PutMapping("/{cId}/transactions/{tId}/update")
  public void updateTransaction(@RequestBody Transaction transaction,
                                @PathVariable("tId") Long id) {
    Transaction transaction2 = transactionService.findById(id);
    transaction.setAccount(transaction2.getAccount());
    transactionService.update(transaction);
  }

  @DeleteMapping("/{caId}")
  public void deleteCurrentAccount(@PathVariable("caId") Long id) {
    savingsAccountService.delete(id);
  }

  @DeleteMapping("/{caId}/transactions/{tId}")
  public void deleteTransaction(@PathVariable("tId") Long id) {
    transactionService.delete(id);
  }
}