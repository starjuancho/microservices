package com.globant.pruebas.profileservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address implements Entity {
	
	private String firstLine;
	private String secondLine;
	private String postalCode;

	public String getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}

	public String getSecondLine() {
		return secondLine;
	}

	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
