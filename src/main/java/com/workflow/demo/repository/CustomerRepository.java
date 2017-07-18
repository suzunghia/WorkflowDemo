package com.workflow.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.workflow.demo.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    public List<Customer> findByFirstName(String firstName); 
}
