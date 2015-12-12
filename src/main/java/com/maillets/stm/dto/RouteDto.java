package com.maillets.stm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maillets.stm.entities.Route;

public class RouteDto {

	@JsonProperty("route_id")
	private Integer id;
	@JsonProperty("agency_id")
	private String agency;
	@JsonProperty("route_short_name")
	private String shortName;
	@JsonProperty("route_long_name")
	private String longName;
	@JsonProperty("route_type")
	private String type;
	@JsonProperty("route_url")
	private String url;
	@JsonProperty("route_color")
	private String color;
	@JsonProperty("route_text_color")
	private String textColor;

	public static RouteDto fromStop(Route route) {
		RouteDto dto = new RouteDto();
		dto.setId(route.getId());
		dto.setAgency(route.getAgency());
		dto.setShortName(route.getShortName());
		dto.setLongName(route.getLongName());
		dto.setType(route.getType());
		dto.setUrl(route.getUrl());
		dto.setColor(route.getColor());
		dto.setTextColor(route.getTextColor());
		return dto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}
}
