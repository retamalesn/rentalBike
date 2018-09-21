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
import com.rentalbike.challenge.model.Discount;

@Transactional
public class DiscountControllerTest extends RentalBikeApplicationTests{

	@Autowired
	private DiscountController discountController;
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void getAllDiscounts() {
		ArrayList<Discount> list = (ArrayList<Discount>) discountController.getDiscounts();
		Assert.assertNotNull("Expected not null", list);
	}
	
	@Test
	public void getDiscountById() {
		Discount discount = discountController.getDiscountById(55L);
		Assert.assertNotNull("Expected not null", discount);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void getDiscountByNoExistId() {
		discountController.getDiscountById(0L);
	}
	
	
	@Test
	public void addDiscount() {
		Discount discount = discountController.createDiscount(new Discount(Constant.DISCOUNT_FAMILY_TYPE, 30, 3, 5, "Description"));
		Assert.assertNotNull("Expected not null", discount);
	}
	
	@Test
	public void updateDiscount() {
		Discount discount = new Discount(Constant.DISCOUNT_FAMILY_TYPE, 30, 3, 5, "Description");
		discount.setId(71L);
		Discount discountUpdated = discountController.updateDiscount(discount);
		Assert.assertNotNull("Expected not null", discountUpdated);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void updateDiscountWhitWrongId() {
		Discount discount = new Discount(Constant.DISCOUNT_FAMILY_TYPE, 30, 3, 5, "Description");
		discount.setId(99L);
		discountController.updateDiscount(discount);

	}
	
	@Test
	public void deleteDiscount() {
		ResponseEntity<?> r = discountController.deleteDiscount(75L);
		Assert.assertTrue(r.getStatusCode().is2xxSuccessful());
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void deleteDiscountWrongId() {
		discountController.deleteDiscount(1000L);
	}
	
	
	
	
}
