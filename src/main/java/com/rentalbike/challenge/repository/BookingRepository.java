package com.rentalbike.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalbike.challenge.model.Booking;


public interface BookingRepository extends JpaRepository<Booking, Long> {

}
