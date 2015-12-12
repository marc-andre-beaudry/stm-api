package com.maillets.stm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maillets.stm.entities.Stop;
import com.maillets.stm.repository.StopRepository;

@Component
public class StopSeedLoader {

	private static final Logger logger = LoggerFactory.getLogger(StopSeedLoader.class);

	@Autowired
	StopRepository stopRepository;

	public void load(String source) {

		InputStream is = StopSeedLoader.class.getResourceAsStream(source);
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
