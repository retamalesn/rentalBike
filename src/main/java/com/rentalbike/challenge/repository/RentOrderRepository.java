package com.rentalbike.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalbike.challenge.model.RentOrder;


public interface RentOrderRepository extends JpaRepository<RentOrder, Long> {

}
