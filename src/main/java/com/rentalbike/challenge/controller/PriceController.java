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
import com.rentalbike.challenge.model.Price;
import com.rentalbike.challenge.repository.PriceRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/prices")
@Api(value="bikes", description="Operations pertaining to prices")
public class PriceController {

	@Autowired
	PriceRepository priceRepository;

	
	@CrossOrigin
	@RequestMapping(value="/all", method=RequestMethod.GET)
    public List<Price> getPrices() {
        return priceRepository.findAll();
    }
	
	@CrossOrigin
	@RequestMapping(value="/price/{id}", method=RequestMethod.GET)
    public Price getPriceById(@PathVariable Long id) {
		return priceRepository.findById(id).map(price -> {
			return price;
		}).orElseThrow(() -> new ResourceNotFoundException("Price not found with id " + id));
    }
	
	@CrossOrigin
	@RequestMapping(value="/priceByType/{type}", method=RequestMethod.GET)
    public List<Price> getPriceByType(@PathVariable String type) {
		return priceRepository.findByType(type).map(price -> {
			return price;
		}).orElseThrow(() -> new ResourceNotFoundException("Prices not found with type " + type));
    }
	
	@CrossOrigin
	@RequestMapping(value="/add", method=RequestMethod.POST)
    public Price createPrice(@Valid @RequestBody Price price) {
        return priceRepository.save(price);
    }
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
    public Price updatePrice(@Valid @RequestBody Price price) {
        return priceRepository.findById(price.getId()).map(priceSaved -> {
        	priceSaved.setPrice(price.getPrice());
        	priceSaved.setType(price.getType());
			return priceRepository.save(priceSaved);
		}).orElseThrow(() -> new ResourceNotFoundException("Price not found with id " + price.getId()));
    }
	
	@ApiOperation(value = "Delete a price", response = ResponseEntity.class)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePrice(@PathVariable Long id) {
		return priceRepository.findById(id).map(price -> {
			priceRepository.delete(price);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Price not found with id " + id));
	}
	
}
