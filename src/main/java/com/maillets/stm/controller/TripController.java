package com.maillets.stm.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.stm.dto.StopDto;
import com.maillets.stm.dto.StopTimeDto;
import com.maillets.stm.dto.TripDto;
import com.maillets.stm.entities.Stop;
import com.maillets.stm.entities.StopTime;
import com.maillets.stm.entities.Trip;
import com.maillets.stm.repository.TripRepository;

import rx.Observable;

@RestController
@RequestMapping("/api/trips")
public class TripController {

	private static final Logger logger = LoggerFactory.getLogger(TripController.class);

	@Autowired
	TripRepository tripRepository;

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public List<TripDto> getTrips(@RequestParam(value = "limit", required = false) Integer limit) {
		logger.debug("GET /");

		if (limit == null) {
			limit = Integer.MAX_VALUE;
		}

		List<TripDto> dtos = new ArrayList<>();
		Pageable pageable = new PageRequest(0, limit, Direction.ASC, "id");
		for (Trip trip : tripRepository.findAll(pageable)) {
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

	@RequestMapping(value = "/{id}/stoptimes", method = { RequestMethod.GET })
	public List<StopTimeDto> getStopTimesForTrip(@PathVariable(value = "id") String id) {
		logger.debug("GET /{id}/stoptimes");

		Trip trip = tripRepository.findOne(id);
		List<StopTimeDto> dtos = new ArrayList<>();
		for (StopTime stopTime : trip.getStopTimes()) {
			dtos.add(StopTimeDto.fromStopTime(stopTime));
		}
		return dtos;
	}

	@RequestMapping(value = "/{id}/stops", method = { RequestMethod.GET })
	public List<StopDto> getStopsForTrip(@PathVariable(value = "id") String id) {
		logger.debug("GET /{id}/stops");

		Trip trip = tripRepository.findOne(id);
		List<StopDto> dtos = new ArrayList<>();
		List<Stop> stops = new ArrayList<>();
		for (StopTime stopTime : trip.getStopTimes()) {
			stops.add(stopTime.getStop());
		}
		Observable.from(stops).distinct(Stop::getId).forEach(stop -> {
			dtos.add(StopDto.fromStop(stop));
		});
		return dtos;
	}
}
