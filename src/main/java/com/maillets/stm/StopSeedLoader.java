package com.maillets.stm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maillets.stm.entities.Stop;

public class StopSeedLoader {

	private static final Logger logger = LoggerFactory.getLogger(StopSeedLoader.class);

	public static List<Stop> load(String source) {

		List<Stop> stops = new ArrayList<Stop>();
		InputStream is = StopSeedLoader.class.getResourceAsStream(source);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			Stream<Stop> stopStream = br.lines().skip(1).map(mapToStop).filter(x -> x != null);
			stops = stopStream.collect(Collectors.toList());
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
		return stops;
	}

	private static Function<String, Stop> mapToStop = (line) -> {
		Stop stop = new Stop();
		try {
			String[] splittedLine = line.split(",");
			stop.setId(Integer.parseInt(splittedLine[0]));
			stop.setCode(Integer.parseInt(splittedLine[1]));
			stop.setName(splittedLine[2]);
			stop.setLatitude(splittedLine[3]);
			stop.setLongitude(splittedLine[4]);
			stop.setUrl(splittedLine[5]);
			stop.setWheelchairBoarding(splittedLine[6]);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return stop;
	};
}
