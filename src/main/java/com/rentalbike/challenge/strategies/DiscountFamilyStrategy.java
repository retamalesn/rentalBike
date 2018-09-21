package com.rentalbike.challenge.strategies;

import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.rentalbike.challenge.model.Booking;
import com.rentalbike.challenge.model.Discount;

@Service
public class DiscountFamilyStrategy implements DiscountStrategy{
	

	@Override
	public Float calculateDiscount(Set<Booking> bookings, Discount discount) {
		
		Float subTotal = 0f;
		Float total = 0f;
		
		if(discount.getMin().compareTo(bookings.size()) <= 0 && 
				discount.getMax().compareTo(bookings.size()) >= 0) {
			Iterator<Booking> iterator = bookings.iterator();
		    while(iterator.hasNext()) {
		    	Booking booking = iterator.next();
		    	subTotal =+ booking.getPrice();
		    }
		    total = ((100-discount.getPercentage())*subTotal)/100;
		    return total;
		}else {
			return total;
		}
		
	}

}
