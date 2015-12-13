package com.maillets.stm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.maillets.stm.repository.RouteRepository;
import com.maillets.stm.repository.StopRepository;
import com.maillets.stm.repository.StopTimeRepository;
import com.maillets.stm.repository.TripRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ClearTable {

	private static final Logger logger = LoggerFactory.getLogger(StmRestServer.class);

	@Autowired
	TripRepository tripRepository;

	@Autowired
	StopRepository stopRepository;

	@Autowired
	StopTimeRepository stopTimeRepository;

	@Autowired
	RouteRepository routeRepository;

	@Bean
	CommandLineRunner init() {

		return arg -> {
			stopTimeRepository.deleteAllInBatch();
			stopRepository.deleteAllInBatch();
			tripRepository.deleteAllInBatch();
			routeRepository.deleteAllInBatch();
			logger.info("Clear Done done!");
			System.exit(0);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ClearTable.class, args);
	}

}
