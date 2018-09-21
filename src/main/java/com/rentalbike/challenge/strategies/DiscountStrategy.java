package com.rentalbike.challenge.strategies;

import java.util.Set;

import com.rentalbike.challenge.model.Booking;
import com.rentalbike.challenge.model.Discount;

public interface DiscountStrategy {
	Float calculateDiscount(Set<Booking> bookings, Discount discount);
}
