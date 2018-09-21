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

import com.rentalbike.challenge.constant.Constant;
import com.rentalbike.challenge.exception.ResourceNotFoundException;
import com.rentalbike.challenge.model.RentOrder;
import com.rentalbike.challenge.repository.DiscountRepository;
import com.rentalbike.challenge.repository.RentOrderRepository;
import com.rentalbike.challenge.strategies.StrategyFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rentOrdes")
@Api(value="rentOrdes", description="Operations pertaining to rent orders")
public class RentOrderController {

	@Autowired
	RentOrderRepository rentOrderRepository;
	@Autowired
	StrategyFactory strategyFactory;
	@Autowired
	DiscountRepository discountRepository;

	
	@CrossOrigin
	@RequestMapping(value="/all", method=RequestMethod.GET)
    public List<RentOrder> getRentOrders() {
        return rentOrderRepository.findAll();
    }
	
	@CrossOrigin
	@RequestMapping(value="/rentOrder/{id}", method=RequestMethod.GET)
    public RentOrder getRentOrderById(@PathVariable Long id) {
		return rentOrderRepository.findById(id).map(rentOrder -> {
			return rentOrder;
		}).orElseThrow(() -> new ResourceNotFoundException("Rent Order not found with id " + id));
    }
	
	
	@CrossOrigin
	@RequestMapping(value="/add", method=RequestMethod.POST)
    public RentOrder createRentOrder(
    		@Valid @RequestBody RentOrder rentOrder) {
        return rentOrderRepository.save(rentOrder);
    }
	
	@CrossOrigin
	@RequestMapping(value="/update", method=RequestMethod.PUT)
    public RentOrder updateRentOrder(@Valid @RequestBody RentOrder rentOrder) {
        return rentOrderRepository.findById(rentOrder.getId()).map(rentOrderSaved -> {
        	rentOrderSaved.setStatus(rentOrder.getStatus());
        	rentOrderSaved.setTotal(rentOrder.getTotal());
			return rentOrderRepository.save(rentOrderSaved);
		}).orElseThrow(() -> new ResourceNotFoundException("Rent Order not found with id " + rentOrder.getId()));
    }
	
	@CrossOrigin
	@RequestMapping(value="/checkout/{id}", method=RequestMethod.PUT)
    public RentOrder checkOutOrder(@PathVariable Long id) {
        return rentOrderRepository.findById(id).map(rentOrderSaved -> {
        	Float total = strategyFactory
                	.getStrategy(Constant.DISCOUNT_FAMILY_TYPE)
                	.calculateDiscount(rentOrderSaved.getBikes(), discountRepository.findByName(Constant.DISCOUNT_FAMILY_TYPE).get());
        	if(!total.equals(0F)) {
        		rentOrderSaved
            	.setTotal(total);
        	}
			return rentOrderRepository.save(rentOrderSaved);
		}).orElseThrow(() -> new ResourceNotFoundException("Rent Order not found with id " + id));
    }
	
	@CrossOrigin
	@ApiOperation(value = "Delete a Rent Order", response = ResponseEntity.class)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRentOrder(@PathVariable Long id) {
		return rentOrderRepository.findById(id).map(rentOrder -> {
			rentOrderRepository.delete(rentOrder);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Rent OrderRepository not found with id " + id));
	}
	
}
