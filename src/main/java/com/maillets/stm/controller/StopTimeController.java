package com.maillets.stm.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.stm.dto.StopTimeDto;
import com.maillets.stm.entities.StopTime;
import com.maillets.stm.repository.StopTimeRepository;

@RestController
@RequestMapping("/api/stoptimes")
public class StopTimeController {

	private static final Logger logger = LoggerFactory.getLogger(StopTimeController.class);

	@Autowired
	StopTimeRepository stopTimeRepository;

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public List<StopTimeDto> getStopTimes(@RequestParam(value = "limit", required = false) Integer limit) {
		logger.debug("GET /");

		if (limit == null) {
			limit = 1000;
		}

		List<StopTimeDto> dtos = new ArrayList<>();
		Pageable pageable = new PageRequest(0, limit, Direction.ASC, "id");
		for (StopTime stopTime : stopTimeRepository.findAll(pageable)) {
			dtos.add(StopTimeDto.fromStopTime(stopTime));
		}

		return dtos;
	}
}
