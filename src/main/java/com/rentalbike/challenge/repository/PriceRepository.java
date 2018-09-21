package com.rentalbike.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rentalbike.challenge.model.Price;


public interface PriceRepository extends JpaRepository<Price, Long> {

	@Query("SELECT p FROM Price p WHERE p.type = ?1")
	Optional<List<Price>> findByType(String type);
	
}
