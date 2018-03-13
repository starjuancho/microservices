package com.globant.pruebas.profileservice;

import org.apache.kafka.common.serialization.StringSerializer;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.pruebas.profileservice.api.ProfileApi;

@SpringBootApplication
public class ProfileServiceApplication extends ResourceConfig {


	/*
	 * Jersey registration
	 */
	public ProfileServiceApplication() {
		register(ProfileApi.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProfileServiceApplication.class,args);	
	}

	/*
	 * Mapper bean to be used by serializer
	 */
	@Bean
	ObjectMapper getObjectMapper () {
		return new ObjectMapper();
	}
	
	/*
	 * Mapper bean to be serializer
	 */
	@Bean
	public StringSerializer getStringSerializer() {
		return new StringSerializer();
	}

}
