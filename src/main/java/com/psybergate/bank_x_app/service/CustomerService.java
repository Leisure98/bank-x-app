package com.psybergate.bank_x_app.service;

import com.psybergate.bank_x_app.domain.customer.Customer;

import java.util.List;

/**
 * The service class for handling business logic for Customers.
 */
@SuppressWarnings({"UnnecessaryModifier", "unused"})
public interface CustomerService {

  public Customer create(Customer customer);

  public void update(Customer customer);

  public void delete(Long customerNum);

  public Customer findById(Long customerNum);

  public List<Customer> findAll();
}