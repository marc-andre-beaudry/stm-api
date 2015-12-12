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

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class StmRestServer {

	private static final Logger logger = LoggerFactory.getLogger(StmRestServer.class);

	@Autowired
	SeedLoader seedLoader;

	@Bean
	CommandLineRunner init() {

		return arg -> {
			seedLoader.loadStops("/stops.txt");
			seedLoader.loadRoutes("/routes.txt");
			seedLoader.loadTrips("/trips.txt");
			logger.info("Init done!");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(StmRestServer.class, args);
	}
}