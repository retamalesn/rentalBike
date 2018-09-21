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
import com.rentalbike.challenge.model.Bike;
import com.rentalbike.challenge.repository.BikeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/bikes")
@Api(value="bikes", description="Operations pertaining to bikes")
public class BikeController {

	@Autowired
	BikeRepository bikeRepository;

	
	@CrossOrigin
	@RequestMapping(value="/all", method=RequestMethod.GET)
    public List<Bike> getBikes() {
        return bikeRepository.findAll();
    }
	
	@CrossOrigin
	@RequestMapping(value="/bike/{id}", method=RequestMethod.GET)
    public Bike getBikeById(@PathVariable Long id) {
		return bikeRepository.findById(id).map(bike -> {
			return bike;
		}).orElseThrow(() -> new ResourceNotFoundException("Bike not found with id " + id));
    }
	
	@CrossOrigin
	@RequestMapping(value="/add", method=RequestMethod.POST)
    public Bike createBike(@Valid @RequestBody Bike bike) {
        return bikeRepository.save(bike);
    }
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
    public Bike updateBike(@Valid @RequestBody Bike bike) {
        return bikeRepository.findById(bike.getId()).map(bikeSaved -> {
        	bikeSaved.setDescription(bike.getDescription());
        	bikeSaved.setStatus(bike.getStatus());
			return bikeRepository.save(bikeSaved);
		}).orElseThrow(() -> new ResourceNotFoundException("Bike not found with id " + bike.getId()));
    }
	
	@ApiOperation(value = "Delete a bike", response = ResponseEntity.class)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBike(@PathVariable Long id) {
		return bikeRepository.findById(id).map(bike -> {
			bikeRepository.delete(bike);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Bike not found with id " + id));
	}
	
}
