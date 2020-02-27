package com.main.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.model.Customer;

@Repository
public interface CustomerService extends JpaRepository<Customer, Integer>{

}
