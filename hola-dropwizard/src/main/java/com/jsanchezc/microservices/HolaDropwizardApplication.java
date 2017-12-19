package com.jsanchezc.microservices;

import javax.ws.rs.client.Client;

import io.dropwizard.client.JerseyClientBuilder;

import com.jsanchezc.microservices.resources.GreeterRestResource;
import com.jsanchezc.microservices.resources.GreeterSayingFactory;
import com.jsanchezc.microservices.resources.HolaRestResource;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HolaDropwizardApplication extends Application<HolaDropwizardConfiguration> {

	public static void main(final String[] args) throws Exception {
		new HolaDropwizardApplication().run(args);
	}

	@Override
	public String getName() {
		return "HolaDropwizard";
	}

	@Override
	public void initialize(final Bootstrap<HolaDropwizardConfiguration> bootstrap) {
		// Enable variable substitution with environment variables
		bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
				bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
	}

	@Override
	public void run(final HolaDropwizardConfiguration configuration, final Environment environment) {

		String saying = configuration.getSayingFactory().getSaying();
		environment.jersey().register(new HolaRestResource(saying));

		// greeter service
		GreeterSayingFactory greeterSayingFactory = configuration.getGreeterSayingFactory();
		Client greeterClient = new JerseyClientBuilder(environment).using(greeterSayingFactory.getJerseyClientConfig())
				.build("greeterClient");
		environment.jersey().register(new GreeterRestResource(greeterSayingFactory.getSaying(),
				greeterSayingFactory.getHost(), greeterSayingFactory.getPort(), greeterClient));
	}

}
