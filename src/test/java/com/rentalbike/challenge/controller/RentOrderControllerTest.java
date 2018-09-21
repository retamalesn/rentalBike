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
import com.rentalbike.challenge.constant.Constant;
import com.rentalbike.challenge.exception.ResourceNotFoundException;
import com.rentalbike.challenge.model.RentOrder;

@Transactional
public class RentOrderControllerTest extends RentalBikeApplicationTests{

	@Autowired
	private RentOrderController rentOrderController;
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void getAllRentOrders() {
		ArrayList<RentOrder> list = (ArrayList<RentOrder>) rentOrderController.getRentOrders();
		Assert.assertNotNull("Expected not null", list);
	}
	
	@Test
	public void getRentOrderById() {
		RentOrder rentOrder = rentOrderController.getRentOrderById(55L);
		Assert.assertNotNull("Expected not null", rentOrder);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void getRentOrderByNoExistId() {
		rentOrderController.getRentOrderById(0L);
	}
	
	
	@Test
	public void addRentOrder() {
		RentOrder rentOrder = rentOrderController.createRentOrder(new RentOrder(150F, Constant.RENT_ORDER_READY));
		Assert.assertNotNull("Expected not null", rentOrder);
	}
	
	@Test
	public void updateRentOrder() {
		RentOrder rentOrder = new RentOrder(150F, Constant.RENT_ORDER_READY);
		rentOrder.setId(74L);
		RentOrder rentOrderUpdated = rentOrderController.updateRentOrder(rentOrder);
		Assert.assertNotNull("Expected not null", rentOrderUpdated);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void updateRentOrderWhitWrongId() {
		RentOrder rentOrder = new RentOrder(150F, Constant.RENT_ORDER_READY);
		rentOrder.setId(99L);
		rentOrderController.updateRentOrder(rentOrder);

	}
	
	@Test
	public void deleteRentOrder() {
		ResponseEntity<?> r = rentOrderController.deleteRentOrder(73L);
		Assert.assertTrue(r.getStatusCode().is2xxSuccessful());
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void deleteRentOrderWrongId() {
		rentOrderController.deleteRentOrder(1000L);
	}
	
	
	
	
}
