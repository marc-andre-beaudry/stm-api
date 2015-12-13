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

import com.maillets.stm.dto.RouteDto;
import com.maillets.stm.dto.StopDto;
import com.maillets.stm.dto.TripDto;
import com.maillets.stm.entities.Route;
import com.maillets.stm.entities.Stop;
import com.maillets.stm.entities.StopTime;
import com.maillets.stm.entities.Trip;
import com.maillets.stm.repository.RouteRepository;

import rx.Observable;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

	private static final Logger logger = LoggerFactory.getLogger(RouteController.class);

	@Autowired
	RouteRepository routeRepository;

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public List<RouteDto> getRoutes(@RequestParam(value = "limit", required = false) Integer limit) {
		logger.debug("GET /");

		if (limit == null) {
			limit = Integer.MAX_VALUE;
		}

		List<RouteDto> dtos = new ArrayList<>();
		Pageable pageable = new PageRequest(0, limit, Direction.ASC, "id");
		for (Route route : routeRepository.findAll(pageable)) {
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

	@RequestMapping(value = "/{id}/stops", method = { RequestMethod.GET })
	public List<StopDto> getStopsForRoute(@PathVariable(value = "id") Integer id) {
		logger.debug("GET /{id}/stops");

		Route route = routeRepository.findOne(id);
		List<StopDto> dtos = new ArrayList<>();
		List<Stop> stops = new ArrayList<>();
		for (Trip trip : route.getTrips()) {
			for (StopTime stopTime : trip.getStopTimes()) {
				stops.add(stopTime.getStop());
			}
		}
		Observable.from(stops).distinct(Stop::getId).forEach(stop -> {
			dtos.add(StopDto.fromStop(stop));
		});
		return dtos;
	}
}
