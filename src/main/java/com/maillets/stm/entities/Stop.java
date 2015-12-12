package com.maillets.stm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stop")
public class Stop {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String code;

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
}
