package com.maillets.stm.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "stop")
public class Stop {

	@Id
	@Column(nullable = false, unique = true)
	private Integer id;

	@Column(nullable = false, unique = true)
	private Integer code;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String latitude;

	@Column(nullable = false)
	private String longitude;

	@Column(nullable = false)
	private String url;

	@Column(nullable = false)
	private String wheelchairBoarding;

	@OneToMany(mappedBy = "stop")
	private Set<StopTime> stopTimes = new HashSet<>();

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

	public Set<StopTime> getStopTimes() {
		return stopTimes;
	}

	public void setStopTimes(Set<StopTime> stopTimes) {
		this.stopTimes = stopTimes;
	}
}
