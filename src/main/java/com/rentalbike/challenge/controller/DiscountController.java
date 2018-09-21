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
import com.rentalbike.challenge.model.Discount;
import com.rentalbike.challenge.repository.DiscountRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/discounts")
@Api(value="discounts", description="Operations pertaining to discounts")
public class DiscountController {

	@Autowired
	DiscountRepository discountRepository;

	
	@CrossOrigin
	@RequestMapping(value="/all", method=RequestMethod.GET)
    public List<Discount> getDiscounts() {
        return discountRepository.findAll();
    }
	
	@CrossOrigin
	@RequestMapping(value="/discount/{id}", method=RequestMethod.GET)
    public Discount getDiscountById(@PathVariable Long id) {
		return discountRepository.findById(id).map(discount -> {
			return discount;
		}).orElseThrow(() -> new ResourceNotFoundException("Discount not found with id " + id));
    }
	
	@CrossOrigin
	@RequestMapping(value="/discountByName/{id}", method=RequestMethod.GET)
    public Discount getDiscountByName(@PathVariable String name) {
		return discountRepository.findByName(name).map(discount -> {
			return discount;
		}).orElseThrow(() -> new ResourceNotFoundException("Discount not found with name " + name));
    }
	
	@CrossOrigin
	@RequestMapping(value="/add", method=RequestMethod.POST)
    public Discount createDiscount(@Valid @RequestBody Discount discount) {
        return discountRepository.save(discount);
    }
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
    public Discount updateDiscount(@Valid @RequestBody Discount discount) {
        return discountRepository.findById(discount.getId()).map(discountSaved -> {
        	discountSaved.setMax(discount.getMax());
        	discountSaved.setMin(discount.getMin());
        	discountSaved.setName(discount.getName());
        	discountSaved.setPercentage(discount.getPercentage());
			return discountRepository.save(discountSaved);
		}).orElseThrow(() -> new ResourceNotFoundException("Discount not found with id " + discount.getId()));
    }
	
	@ApiOperation(value = "Delete a discount", response = ResponseEntity.class)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteDiscount(@PathVariable Long id) {
		return discountRepository.findById(id).map(discount -> {
			discountRepository.delete(discount);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Discount not found with id " + id));
	}
	
}
