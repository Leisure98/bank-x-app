package com.psybergate.bank_x_app.repository;

import com.psybergate.bank_x_app.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository class for storing and retrieving Customers to and from the database.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}