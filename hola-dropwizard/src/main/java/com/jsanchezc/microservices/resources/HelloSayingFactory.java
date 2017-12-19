package com.jsanchezc.microservices.resources;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HelloSayingFactory {

	@NotEmpty
	private String saying;

	@JsonProperty
	public String getSaying() {
		return saying;
	}

	@JsonProperty
	public void setSaying(String saying) {
		this.saying = saying;
	}



}
