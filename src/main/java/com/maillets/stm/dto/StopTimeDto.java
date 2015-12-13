package com.maillets.stm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maillets.stm.entities.StopTime;

public class StopTimeDto {

	@JsonProperty("trip_id")
	private String tripId;
	@JsonProperty("arrival_time")
	private String arrivalTime;
	@JsonProperty("departure_time")
	private String departureTime;
	@JsonProperty("stop_id")
	private String stopId;
	@JsonProperty("stop_sequence")
	private String stopSequence;

	public static StopTimeDto fromStopTime(StopTime stopTime) {
		StopTimeDto dto = new StopTimeDto();
		dto.setArrivalTime(stopTime.getArrivalTime().toString());
		dto.setDepartureTime(stopTime.getDepartureTime().toString());
		dto.setStopId(stopTime.getStop().getId().toString());
		dto.setStopSequence(stopTime.getStopSequence().toString());
		dto.setTripId(stopTime.getTrip().getId().toString());
		return dto;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getStopId() {
		return stopId;
	}

	public void setStopId(String stopId) {
		this.stopId = stopId;
	}

	public String getStopSequence() {
		return stopSequence;
	}

	public void setStopSequence(String stopSequence) {
		this.stopSequence = stopSequence;
	}
}
