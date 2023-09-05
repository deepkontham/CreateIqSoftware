package com.example.demo.payload;

import java.util.Date;

public class ErrorDetails {
	private Date timesStamp;
	private String Mesaage;
	private String Details;

	public ErrorDetails(Date timesStamp, String mesaage, String details) {
		super();
		this.timesStamp = timesStamp;
		Mesaage = mesaage;
		Details = details;
	}

	public Date getTimesStamp() {
		return timesStamp;
	}

	public String getMesaage() {
		return Mesaage;
	}

	public String getDetails() {
		return Details;
	}

}
