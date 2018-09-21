package com.rentalbike.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalbike.challenge.model.Bike;


public interface BikeRepository extends JpaRepository<Bike, Long> {

}
