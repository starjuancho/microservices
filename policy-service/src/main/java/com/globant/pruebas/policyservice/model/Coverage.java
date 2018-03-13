package com.globant.pruebas.policyservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class Coverage implements Entity {

	private CoverageType type;

	public CoverageType getType() {
		return type;
	}

	public void setType(CoverageType type) {
		this.type = type;
	}
	
}
