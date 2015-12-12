package com.maillets.stm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.stm.dto.TripDto;
import com.maillets.stm.entities.Trip;
import com.maillets.stm.repository.TripRepository;

@RestController
@RequestMapping("/api/trips")
public class TripController {

	private static final Logger logger = LoggerFactory.getLogger(TripController.class);

	@Autowired
	TripRepository tripRepository;

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public List<TripDto> getTrips(@RequestParam(value = "limit", required = false) Long limit) {
		logger.debug("GET /");

		if (limit == null) {
			limit = Long.MAX_VALUE;
		}

		List<TripDto> dtos = new ArrayList<>();
		for (Trip trip : tripRepository.findAll().stream().limit(limit).collect(Collectors.toList())) {
			dtos.add(TripDto.fromTrip(trip));
		}

		return dtos;
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public TripDto getTripById(@PathVariable(value = "id") String id) {
		logger.debug("GET /{id}");

		Trip trip = tripRepository.findOne(id);
		return TripDto.fromTrip(trip);
	}
}
