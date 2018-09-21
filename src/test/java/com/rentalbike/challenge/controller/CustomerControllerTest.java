package com.rentalbike.challenge.controller;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.rentalbike.challenge.RentalBikeApplicationTests;
import com.rentalbike.challenge.exception.ResourceNotFoundException;
import com.rentalbike.challenge.model.Customer;

@Transactional
public class CustomerControllerTest extends RentalBikeApplicationTests{

	@Autowired
	private CustomerController customerController;
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void getAllCustomers() {
		ArrayList<Customer> list = (ArrayList<Customer>) customerController.getCustomers();
		Assert.assertNotNull("Expected not null", list);
	}
	
	@Test
	public void getCustomerById() {
		Customer customer = customerController.getCustomerById(55L);
		Assert.assertNotNull("Expected not null", customer);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void getCustomerByNoExistId() {
		customerController.getCustomerById(0L);
	}
	
	
	@Test
	public void addCustomer() {
		Customer customer = customerController.createCustomer(
				new Customer("Jhon", "Doe", "jhond", "123456", "35989787", "avatar.jpg"));
		Assert.assertNotNull("Expected not null", customer);
	}
	
	@Test
	public void updateCustomer() {
		Customer customer = new Customer("Jhon", "Doe", "jhond", "123456", "35989787", "avatar.jpg");
		customer.setId(76L);
		Customer customerUpdated = customerController.updateCustomer(customer);
		Assert.assertNotNull("Expected not null", customerUpdated);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void updateCustomerWhitWrongId() {
		Customer customer = new Customer("Jhon", "Doe", "jhond", "123456", "35989787", "avatar.jpg");
		customer.setId(99L);
		customerController.updateCustomer(customer);

	}
	
	@Test
	public void deleteCustomer() {
		ResponseEntity<?> r = customerController.deleteCustomer(72L);
		Assert.assertTrue(r.getStatusCode().is2xxSuccessful());
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void deleteCustomerWrongId() {
		customerController.deleteCustomer(1000L);
	}
	
	
	
	
}
