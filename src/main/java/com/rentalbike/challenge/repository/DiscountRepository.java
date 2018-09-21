package com.rentalbike.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rentalbike.challenge.model.Discount;


public interface DiscountRepository extends JpaRepository<Discount, Long> {
	
	@Query("SELECT d FROM Discount d WHERE d.name = ?1")
	Optional<Discount> findByName(String name);
}
