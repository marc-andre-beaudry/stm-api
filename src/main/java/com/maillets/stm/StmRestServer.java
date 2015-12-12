package com.maillets.stm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.maillets.stm.entities.Stop;
import com.maillets.stm.repository.StopRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class StmRestServer {

	private static final Logger logger = LoggerFactory.getLogger(StmRestServer.class);

	@Autowired
	StopRepository stopRepository;

	@Bean
	CommandLineRunner init() {

		return arg -> {
			logger.info("Init done!");
			List<Stop> stops = StopSeedLoader.load("/stops.txt");
			stopRepository.save(stops);
			stopRepository.flush();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(StmRestServer.class, args);
	}
}