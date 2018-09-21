package com.rentalbike.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalbike.challenge.model.Image;


public interface ImageRepository extends JpaRepository<Image, Long> {

}
