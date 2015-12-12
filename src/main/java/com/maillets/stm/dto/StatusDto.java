package com.maillets.stm.dto;

public class StatusDto {

	public enum Status {
		GREEN, AMBER, RED
	}

	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
