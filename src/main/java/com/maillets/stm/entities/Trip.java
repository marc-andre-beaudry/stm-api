package com.maillets.stm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trip")
public class Trip {

	@Id
	@Column(nullable = false, unique = true)
	private String id;

	@Column(nullable = false)
	private Integer routeId;

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
