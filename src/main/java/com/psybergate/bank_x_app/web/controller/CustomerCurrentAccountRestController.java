package com.psybergate.bank_x_app.web.controller;

import com.psybergate.bank_x_app.domain.Transaction.Transaction;
import com.psybergate.bank_x_app.domain.account.CurrentAccount;
import com.psybergate.bank_x_app.service.CurrentAccountService;
import com.psybergate.bank_x_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/customers/{id}/current-accounts")
@SuppressWarnings("unused")
public class CustomerCurrentAccountRestController {

  private final CurrentAccountService currentAccountService;

  private final TransactionService transactionService;

  @Autowired
  public CustomerCurrentAccountRestController(CurrentAccountService currentAccountService, TransactionService transactionService) {
    this.currentAccountService = currentAccountService;
    this.transactionService = transactionService;
  }

  @GetMapping
  public List<CurrentAccount> currentAccountsForCustomer(@PathVariable("id") Long id) {
    return currentAccountService.findByCustomerId(id);
  }

  @GetMapping("/{caId}")
  public CurrentAccount currentAccountById(@PathVariable("caId") Long id) {
    CurrentAccount currentAccount = currentAccountService.findById(id);

    if (currentAccount != null) {
      List<Transaction> transactions =
          transactionService.findAllTransactions(currentAccount.getAccountNum());
      transactions.sort(Comparator.comparing(Transaction::getDate));
      currentAccount.setTransactions(transactions);
    }

    return currentAccount;
  }

  @PostMapping
  public CurrentAccount createCurrentAccount(@Valid @RequestBody CurrentAccount currentAccount) {
    return currentAccountService.create(currentAccount);
  }

  @PostMapping("/{caId}/events")
  public void createTransaction(@PathVariable("caId") Long id, @RequestBody Transaction transaction) {
    CurrentAccount currentAccount = currentAccountService.findById(id);
    transaction.setAccount(currentAccount);
    transactionService.create(transaction);
  }

  @PutMapping("/{caId}/update")
  public void updateCurrentAccount(@Valid @RequestBody CurrentAccount currentAccount,
                                   @PathVariable("caId") Long id) {
    currentAccountService.update(currentAccount);
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
    currentAccountService.delete(id);
  }

  @DeleteMapping("/{caId}/transactions/{tId}")
  public void deleteTransaction(@PathVariable("tId") Long id) {
    transactionService.delete(id);
  }
}