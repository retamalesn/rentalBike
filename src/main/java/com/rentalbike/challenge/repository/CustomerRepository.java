package com.rentalbike.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalbike.challenge.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
