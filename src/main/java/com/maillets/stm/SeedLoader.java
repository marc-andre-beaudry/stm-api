package com.maillets.stm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maillets.stm.entities.Route;
import com.maillets.stm.entities.Stop;
import com.maillets.stm.entities.StopTime;
import com.maillets.stm.entities.Trip;
import com.maillets.stm.repository.RouteRepository;
import com.maillets.stm.repository.StopRepository;
import com.maillets.stm.repository.StopTimeRepository;
import com.maillets.stm.repository.TripRepository;

@Component
public class SeedLoader {

	private static final Logger logger = LoggerFactory.getLogger(SeedLoader.class);

	@Autowired
	StopRepository stopRepository;

	@Autowired
	RouteRepository routeRepository;

	@Autowired
	TripRepository tripRepository;

	@Autowired
	StopTimeRepository stopTimeRepository;

	public void loadRoutes(String source) {
		InputStream is = SeedLoader.class.getResourceAsStream(source);
		List<Route> routes = new ArrayList<>();
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
				routes.add(route);
			});
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
		routeRepository.save(routes);
		routeRepository.flush();
	}

	public void loadStops(String source) {

		InputStream is = SeedLoader.class.getResourceAsStream(source);
		List<Stop> stops = new ArrayList<>();
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
				stops.add(stop);
			});
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
		stopRepository.save(stops);
		stopRepository.flush();
	}

	public void loadTrips(String source) {
		InputStream is = SeedLoader.class.getResourceAsStream(source);
		List<Trip> trips = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			br.lines().skip(1).forEach(line -> {
				Trip trip = new Trip();
				String[] splittedLine = line.split(",");
				Integer routeId = Integer.parseInt(splittedLine[0]);
				Route route = routeRepository.findOne(routeId);
				trip.setRoute(route);
				trip.setServiceId(splittedLine[1]);
				trip.setId(splittedLine[2]);
				trip.setHeadSign(splittedLine[3]);
				trip.setDirectionId(splittedLine[4]);
				if (splittedLine.length > 5) {
					trip.setShapeId(splittedLine[5]);
				}
				if (splittedLine.length > 6) {
					trip.setWheelchairAccessible(splittedLine[6]);
				}
				if (splittedLine.length > 7) {
					trip.setNoteFr(splittedLine[7]);
				}
				if (splittedLine.length > 8) {
					trip.setNoteEn(splittedLine[8]);
				}
				trips.add(trip);
			});
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
		tripRepository.save(trips);
		tripRepository.flush();
	}

	public void loadStopTimes(String source) {
		InputStream is = SeedLoader.class.getResourceAsStream(source);
		List<StopTime> stopTimes = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			br.lines().skip(1).forEach(line -> {
				try {
					StopTime stopTime = new StopTime();
					String[] splittedLine = line.split(",");
					Trip trip = tripRepository.findOne(splittedLine[0]);
					Integer stopId = Integer.parseInt(splittedLine[3]);
					Stop stop = stopRepository.findOne(stopId);

					stopTime.setTrip(trip);
					stopTime.setStop(stop);
					stopTime.setArrivalTime(splittedLine[1]);
					stopTime.setDepartureTime(splittedLine[2]);
					stopTime.setStopSequence(Integer.parseInt(splittedLine[4]));

					stopTimes.add(stopTime);
				} catch (Exception e) {
				}
			});
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
		stopTimeRepository.save(stopTimes);
		stopTimeRepository.flush();
	}
}
