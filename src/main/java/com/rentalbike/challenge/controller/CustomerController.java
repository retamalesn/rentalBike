package com.rentalbike.challenge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentalbike.challenge.exception.ResourceNotFoundException;
import com.rentalbike.challenge.model.Customer;
import com.rentalbike.challenge.repository.CustomerRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customers")
@Api(value="customers", description="Operations pertaining to customers")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	
	@CrossOrigin
	@RequestMapping(value="/all", method=RequestMethod.GET)
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
	
	@CrossOrigin
	@RequestMapping(value="/customer/{id}", method=RequestMethod.GET)
    public Customer getCustomerById(@PathVariable Long id) {
		return customerRepository.findById(id).map(customer -> {
			return customer;
		}).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }
	
	@CrossOrigin
	@RequestMapping(value="/add", method=RequestMethod.POST)
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
    public Customer updateCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.findById(customer.getId()).map(customerSaved -> {
        	customerSaved.setCustomerName(customer.getCustomerName());
        	customerSaved.setIdentification(customer.getIdentification());
        	customerSaved.setImageUrl(customer.getImageUrl());
        	customerSaved.setLastName(customer.getLastName());
        	customerSaved.setName(customer.getName());
        	customerSaved.setPass(customer.getPass());
			return customerRepository.save(customerSaved);
		}).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + customer.getId()));
    }
	
	@ApiOperation(value = "Delete a customer", response = ResponseEntity.class)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
		return customerRepository.findById(id).map(customer -> {
			customerRepository.delete(customer);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
	}
	
}
