package com.jsanchezc.wfswarm.rest;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.ConfigProperty;

@Path("/api")
public class GreeterResource {

  @Inject
  @ConfigProperty(name = "WF_SWARM_SAYING", defaultValue = "Hola")
  private String saying;

  @Inject
	@ConfigProperty(name = "GREETING_BACKEND_SERVICE_HOST", defaultValue = "localhost")
	private String backendServiceHost;

	@Inject
	@ConfigProperty(name = "GREETING_BACKEND_SERVICE_PORT", defaultValue = "8080")
	private int backendServicePort;

	@GET
	@Path("/greeting")
	@Produces("text/plain")
	public String doGet() {
		String backendServiceUrl = String.format("http://%s:%d", backendServiceHost,backendServicePort);
		System.out.println("Sending to: " + backendServiceUrl);

		Client client = ClientBuilder.newClient();
		BackendDTO backendDTO = client.target(backendServiceUrl)
		.path("api")
		.path("backend")
		.queryParam("greeting", saying)
		.request(MediaType.APPLICATION_JSON_TYPE)
		.get(BackendDTO.class);
		
		return backendDTO.getGreeting() + " at host: " + backendDTO.getIp() + ", time: "+backendDTO.getTime();
	}
}