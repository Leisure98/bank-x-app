package com.psybergate.bank_x_app.web.controller;

import com.psybergate.bank_x_app.domain.account.CurrentAccount;
import com.psybergate.bank_x_app.domain.account.SavingsAccount;
import com.psybergate.bank_x_app.domain.customer.Customer;
import com.psybergate.bank_x_app.service.CurrentAccountService;
import com.psybergate.bank_x_app.service.CustomerService;
import com.psybergate.bank_x_app.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
@SuppressWarnings("unused")
public class CustomerRestController {

  private final CustomerService customerService;

  private final CurrentAccountService currentAccountService;

  private final SavingsAccountService savingsAccountService;

  @Autowired
  public CustomerRestController(CustomerService customerService, CurrentAccountService currentAccountService, SavingsAccountService savingsAccountService) {
    this.customerService = customerService;
    this.currentAccountService = currentAccountService;
    this.savingsAccountService = savingsAccountService;
  }

  @GetMapping
  public List<Customer> customers() {
    return customerService.findAll();
  }

  @GetMapping("/{id}")
  public Customer customerById(@PathVariable("id") Long id) {
    return customerService.findById(id);
  }

  @PostMapping
  public Customer createCustomer(@Valid @RequestBody Customer customer) {
    Customer customerSaved = customerService.create(customer);

    CurrentAccount currentAccount = new CurrentAccount();
    currentAccount.setCustomer(customerSaved);
    currentAccountService.create(currentAccount);

    SavingsAccount savingsAccount = SavingsAccount.newCustomer();
    savingsAccount.setCustomer(customerSaved);
    savingsAccountService.create(savingsAccount);


    customerSaved.addCurrentAccount(currentAccount);
    customerSaved.addSavingsAccount(savingsAccount);
    customerService.update(customerSaved);
    return customerSaved;
  }

  @DeleteMapping("/{id}")
  public void deleteCustomer(@PathVariable("id") Long id) {
    customerService.delete(id);
  }

  @PutMapping("/{id}/update")
  public void updateCustomer(@Valid @RequestBody Customer customer, @PathVariable("id") Long id) {
    customerService.update(customer);
  }
}