package com.infy.repository;

import org.springframework.data.repository.CrudRepository;
import com.infy.customer.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
