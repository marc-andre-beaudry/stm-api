package com.maillets.stm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.stm.entities.Trip;

public interface TripRepository extends JpaRepository<Trip, String> {

}
