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
import com.rentalbike.challenge.model.Price;

@Transactional
public class PriceControllerTest extends RentalBikeApplicationTests{

	@Autowired
	private PriceController priceController;
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void getAllPrices() {
		ArrayList<Price> list = (ArrayList<Price>) priceController.getPrices();
		Assert.assertNotNull("Expected not null", list);
	}
	
	@Test
	public void getPriceById() {
		Price price = priceController.getPriceById(39L);
		Assert.assertNotNull("Expected not null", price);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void getPriceByNoExistId() {
		priceController.getPriceById(0L);
	}
	
	@Test
	public void getPriceByType() {
		ArrayList<Price> price = (ArrayList<Price>) priceController.getPriceByType(Constant.PRICE_DAILY);
		Assert.assertNotNull("Expected not null", price);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void getPriceByWrongId() {
		priceController.getPriceByType("DAILYS");
	}
	
	@Test
	public void addPrice() {
		Price price = priceController.createPrice(new Price(Constant.PRICE_DAILY, 34F));
		Assert.assertNotNull("Expected not null", price);
	}
	
	@Test
	public void updatePrice() {
		Price price = new Price(Constant.PRICE_DAILY, 34F);
		price.setId(38L);
		Price priceUpdated = priceController.updatePrice(price);
		Assert.assertNotNull("Expected not null", priceUpdated);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void updatePriceWhitWrongId() {
		Price price = new Price(Constant.PRICE_DAILY, 34F);
		price.setId(99L);
		priceController.updatePrice(price);

	}
	
	@Test
	public void deletePrice() {
		ResponseEntity<?> r = priceController.deletePrice(38L);
		Assert.assertTrue(r.getStatusCode().is2xxSuccessful());
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void deletePriceWrongId() {
		priceController.deletePrice(1000L);
	}
	
	
	
	
}
