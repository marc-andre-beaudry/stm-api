package com.maillets.stm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.activemq.filter.function.splitFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maillets.stm.entities.Route;
import com.maillets.stm.entities.Stop;
import com.maillets.stm.repository.RouteRepository;
import com.maillets.stm.repository.StopRepository;

@Component
public class SeedLoader {

	private static final Logger logger = LoggerFactory.getLogger(SeedLoader.class);

	@Autowired
	StopRepository stopRepository;

	@Autowired
	RouteRepository routeRepository;

	public void loadRoutes(String source) {
		InputStream is = SeedLoader.class.getResourceAsStream(source);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			br.lines().skip(1).forEach(line -> {
				Route route = new Route();
				String[] splittedLine = line.split(",");
				route.setId(Integer.parseInt(splittedLine[0]));
				route.setAgency(splittedLine[1]);
				route.setShortName(splittedLine[2]);
				route.setLongName(splittedLine[3]);
				route.setType(splittedLine[4]);
				route.setUrl(splittedLine[5]);
				route.setColor(splittedLine[6]);
				if (splittedLine.length == 8) {
					route.setTextColor(splittedLine[7]);
				}
				routeRepository.saveAndFlush(route);
			});
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	public void loadStops(String source) {

		InputStream is = SeedLoader.class.getResourceAsStream(source);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			br.lines().skip(1).forEach(line -> {
				Stop stop = new Stop();
				String[] splittedLine = line.split(",");
				stop.setId(Integer.parseInt(splittedLine[0]));
				stop.setCode(Integer.parseInt(splittedLine[1]));
				stop.setName(splittedLine[2]);
				stop.setLatitude(splittedLine[3]);
				stop.setLongitude(splittedLine[4]);
				stop.setUrl(splittedLine[5]);
				stop.setWheelchairBoarding(splittedLine[6]);
				stopRepository.saveAndFlush(stop);
			});
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
	}
}
