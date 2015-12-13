package com.maillets.stm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.stm.entities.StopTime;

public interface StopTimeRepository extends JpaRepository<StopTime, Integer> {

}
