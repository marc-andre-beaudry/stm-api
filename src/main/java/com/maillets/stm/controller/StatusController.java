package com.maillets.stm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.stm.dto.StatusDto;
import com.maillets.stm.dto.StatusDto.Status;

@RestController
@RequestMapping("/api/status")
public class StatusController {

	private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public StatusDto getStatus() {
		logger.debug("GET /");

		StatusDto status = new StatusDto();
		status.setStatus(Status.GREEN);

		return status;
	}
}
