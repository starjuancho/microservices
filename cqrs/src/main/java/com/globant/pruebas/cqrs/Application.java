package com.globant.pruebas.cqrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	
	private static ConfigurableApplicationContext configuragleAppContext = null;
	
	public static void main(String[] args) {
		
		configuragleAppContext = SpringApplication.run(TestApplication.class, args);
	}
	
	public ConfigurableApplicationContext getConfiguragleAppContext () {
		return configuragleAppContext;
	}
	
}
