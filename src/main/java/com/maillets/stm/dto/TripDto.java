package com.maillets.stm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maillets.stm.entities.Trip;

public class TripDto {

	@JsonProperty("trip_id")
	private String id;
	@JsonProperty("route_id")
	private Integer routeId;
	@JsonProperty("service_id")
	private String serviceId;
	@JsonProperty("trip_headsign")
	private String headSign;
	@JsonProperty("direction_id")
	private String directionId;
	@JsonProperty("shape_id")
	private String shapeId;
	@JsonProperty("wheelchair_accessible")
	private String wheelchairAccessible;
	@JsonProperty("note_fr")
	private String noteFr;
	@JsonProperty("note_en")
	private String noteEn;

	public static TripDto fromTrip(Trip trip) {
		TripDto dto = new TripDto();
		dto.setId(trip.getId().toString());
		dto.setRouteId(trip.getRoute().getId());
		dto.setServiceId(trip.getServiceId());
		dto.setHeadSign(trip.getHeadSign());
		dto.setDirectionId(trip.getDirectionId());
		dto.setShapeId(trip.getShapeId());
		dto.setWheelchairAccessible(trip.getWheelchairAccessible());
		dto.setNoteFr(trip.getNoteFr());
		dto.setNoteEn(trip.getNoteEn());
		return dto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getHeadSign() {
		return headSign;
	}

	public void setHeadSign(String headSign) {
		this.headSign = headSign;
	}

	public String getDirectionId() {
		return directionId;
	}

	public void setDirectionId(String directionId) {
		this.directionId = directionId;
	}

	public String getShapeId() {
		return shapeId;
	}

	public void setShapeId(String shapeId) {
		this.shapeId = shapeId;
	}

	public String getWheelchairAccessible() {
		return wheelchairAccessible;
	}

	public void setWheelchairAccessible(String wheelchairAccessible) {
		this.wheelchairAccessible = wheelchairAccessible;
	}

	public String getNoteFr() {
		return noteFr;
	}

	public void setNoteFr(String noteFr) {
		this.noteFr = noteFr;
	}

	public String getNoteEn() {
		return noteEn;
	}

	public void setNoteEn(String noteEn) {
		this.noteEn = noteEn;
	}
}
