package com.rentalbike.challenge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentalbike.challenge.model.Booking;
import com.rentalbike.challenge.repository.BookingRepository;
import com.rentalbike.challenge.repository.RentOrderRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/bookings")
@Api(value = "bookings", description = "Operations pertaining to bookings")
public class BookingController {

	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	RentOrderRepository rentOrderRepository;

	@CrossOrigin
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Booking> getBookings() {
		return bookingRepository.findAll();
	}

	@CrossOrigin
	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public Booking addToCart(@Valid @RequestBody Booking booking) {
		if(booking.getRentOrder() != null && booking.getRentOrder().getId() == null) {
			booking.setRentOrder(rentOrderRepository.save(booking.getRentOrder()));
		}
		return bookingRepository.save(booking);
	}

}
