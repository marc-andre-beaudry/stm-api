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

import com.maillets.stm.dto.StopDto;
import com.maillets.stm.entities.Stop;
import com.maillets.stm.repository.StopRepository;

@RestController
@RequestMapping("/api/stops")
public class StopController {

	private static final Logger logger = LoggerFactory.getLogger(StopController.class);

	@Autowired
	StopRepository stopRepository;

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public List<StopDto> getStops(@RequestParam(value = "limit", required = false) Long limit) {
		logger.debug("GET /");

		if (limit == null) {
			limit = Long.MAX_VALUE;
		}

		List<StopDto> dtos = new ArrayList<>();
		for (Stop stop : stopRepository.findAll().stream().limit(limit).collect(Collectors.toList())) {
			dtos.add(StopDto.fromStop(stop));
		}

		return dtos;
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public StopDto getStopById(@PathVariable(value = "id") Integer id) {
		logger.debug("GET /{id}");

		Stop stop = stopRepository.findOne(id);
		return StopDto.fromStop(stop);
	}
}
