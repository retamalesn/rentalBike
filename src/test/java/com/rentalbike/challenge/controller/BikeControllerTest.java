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
import com.rentalbike.challenge.model.Bike;

@Transactional
public class BikeControllerTest extends RentalBikeApplicationTests{

	@Autowired
	private BikeController bikeController;
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	
	@Test
	public void getAllBikes() {
		ArrayList<Bike> list = (ArrayList<Bike>) bikeController.getBikes();
		Assert.assertNotNull("Expected not null", list);
	}
	
	@Test
	public void getBikeById() {
		Bike bike = bikeController.getBikeById(55L);
		Assert.assertNotNull("Expected not null", bike);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void getBikeByNoExistId() {
		bikeController.getBikeById(0L);
	}
	
	
	@Test
	public void addBike() {
		Bike bike = bikeController.createBike(new Bike("A new test Bike", "AVAILABLE"));
		Assert.assertNotNull("Expected not null", bike);
	}
	
	@Test
	public void updateBike() {
		Bike bike = new Bike("A new test Bike", "AVAILABLE");
		bike.setId(55L);
		Bike bikeUpdated = bikeController.updateBike(bike);
		Assert.assertNotNull("Expected not null", bikeUpdated);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void updateBikeWhitWrongId() {
		Bike bike = new Bike("A new test Bike", "AVAILABLE");
		bike.setId(99L);
		bikeController.updateBike(bike);

	}
	
	@Test
	public void deleteBike() {
		ResponseEntity<?> r = bikeController.deleteBike(59L);
		Assert.assertTrue(r.getStatusCode().is2xxSuccessful());
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void deleteBikeWrongId() {
		bikeController.deleteBike(1000L);
	}
	
	
	
	
}
