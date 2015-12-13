package com.maillets.stm.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "trip")
public class Trip {

	@Id
	@Column(nullable = false, unique = true)
	private String id;

	@Column(nullable = false)
	private String serviceId;

	@Column(nullable = false)
	private String headSign;

	@Column(nullable = true)
	private String directionId;

	@Column(nullable = true)
	private String shapeId;

	@Column(nullable = true)
	private String wheelchairAccessible;

	@Column(nullable = true)
	private String noteFr;

	@Column(nullable = true)
	private String noteEn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "routeId")
	private Route route;

	@OneToMany(mappedBy = "trip")
	private Set<StopTime> stopTimes = new HashSet<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Set<StopTime> getStopTimes() {
		return stopTimes;
	}

	public void setStopTimes(Set<StopTime> stopTimes) {
		this.stopTimes = stopTimes;
	}
}
