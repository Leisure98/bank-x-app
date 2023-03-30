package com.psybergate.bank_x_app.service.impl;

import com.psybergate.bank_x_app.domain.customer.Customer;
import com.psybergate.bank_x_app.exception.CustomerException;
import com.psybergate.bank_x_app.repository.CustomerRepository;
import com.psybergate.bank_x_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("customerService")
@Transactional
@SuppressWarnings("unused")
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Customer create(Customer customer) {
    return customerRepository.save(customer);
  }

  @Override
  public void update(Customer customer) {
    customerRepository.save(customer);
  }

  @Override
  public void delete(Long customerNum) {
    Customer customer = findById(customerNum);
    if (customer == null) {
      throw new CustomerException("Customer doesn't exist");
    }

    //TODO: Change to soft delete
    //Does a hard delete. Soft Delete is recommended where the data is kept but the user doesn't
    // have access to it.
    customerRepository.delete(customer);
  }

  @Override
  public Customer findById(Long customerNum) {
    Optional<Customer> customer = customerRepository.findById(customerNum);
    return customer.orElse(null);
  }

  @Override
  public List<Customer> findAll() {
    return customerRepository.findAll();
  }
}