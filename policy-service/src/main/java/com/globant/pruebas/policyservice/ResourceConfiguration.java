package com.globant.pruebas.policyservice;

import org.springframework.context.annotation.Configuration;
import org.glassfish.jersey.server.ResourceConfig;

@Configuration
public class ResourceConfiguration extends ResourceConfig {

	/*
	 * Jersey registration
	 */
	public ResourceConfiguration() {
		register(com.globant.pruebas.policyservice.api.PolicyServiceAPI.class);
	}


}