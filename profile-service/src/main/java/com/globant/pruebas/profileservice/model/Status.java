package com.globant.pruebas.profileservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Status implements Entity {
	
	private Short status;
	private String name;
	private String descriptions;

	public Status () {
	}

	public Status (Short status, String name, String description) {
		this.status = status;
		this.name = name;
		this.descriptions = description;
	}
	
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

}
