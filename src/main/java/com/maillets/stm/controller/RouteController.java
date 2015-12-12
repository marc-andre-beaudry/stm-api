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

import com.maillets.stm.dto.RouteDto;
import com.maillets.stm.dto.TripDto;
import com.maillets.stm.entities.Route;
import com.maillets.stm.entities.Trip;
import com.maillets.stm.repository.RouteRepository;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

	private static final Logger logger = LoggerFactory.getLogger(RouteController.class);

	@Autowired
	RouteRepository routeRepository;

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public List<RouteDto> getRoutes(@RequestParam(value = "limit", required = false) Long limit) {
		logger.debug("GET /");

		if (limit == null) {
			limit = Long.MAX_VALUE;
		}

		List<RouteDto> dtos = new ArrayList<>();
		for (Route route : routeRepository.findAll().stream().limit(limit).collect(Collectors.toList())) {
			dtos.add(RouteDto.fromStop(route));
		}

		return dtos;
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public RouteDto getRouteById(@PathVariable(value = "id") Integer id) {
		logger.debug("GET /{id}");

		Route route = routeRepository.findOne(id);
		return RouteDto.fromStop(route);
	}

	@RequestMapping(value = "/{id}/trips", method = { RequestMethod.GET })
	public List<TripDto> getTripsForRoute(@PathVariable(value = "id") Integer id) {
		logger.debug("GET /{id}/trips");

		Route route = routeRepository.findOne(id);
		List<TripDto> dtos = new ArrayList<>();
		for (Trip trip : route.getTrips()) {
			dtos.add(TripDto.fromTrip(trip));
		}
		return dtos;
	}
}
