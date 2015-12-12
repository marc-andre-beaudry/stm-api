package com.maillets.stm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maillets.stm.entities.Stop;

public class StopDto {

	private Integer id;
	private Integer code;
	private String name;
	@JsonProperty("lat")
	private String latitude;
	@JsonProperty("long")
	private String longitude;
	private String url;
	private String wheelchairBoarding;

	public static StopDto fromStop(Stop stop) {
		StopDto dto = new StopDto();
		dto.setId(stop.getId());
		dto.setCode(stop.getCode());
		dto.setName(stop.getName());
		dto.setLatitude(stop.getLatitude());
		dto.setLongitude(stop.getLongitude());
		dto.setUrl(stop.getUrl());
		dto.setWheelchairBoarding(stop.getWheelchairBoarding());
		return dto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWheelchairBoarding() {
		return wheelchairBoarding;
	}

	public void setWheelchairBoarding(String wheelchairBoarding) {
		this.wheelchairBoarding = wheelchairBoarding;
	}
}
