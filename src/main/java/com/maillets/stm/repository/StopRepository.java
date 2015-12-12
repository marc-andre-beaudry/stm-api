package com.maillets.stm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.stm.entities.Stop;

public interface StopRepository extends JpaRepository<Stop, Integer> {

	public List<Stop> findByName(String name);
}
