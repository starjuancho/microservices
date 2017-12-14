package com.jsanchezc.examples.holaspringboot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
@ConfigurationProperties(prefix = "greeting")
public class GreeterRestController {

	private String saying;
	private String backendServiceHost;
	private String prefix;
	private String service;
	private int backendServicePort;
	
	private RestTemplate template = new RestTemplate();

	@RequestMapping(value = "/greeting", method = RequestMethod.GET, produces = "text/plain")
	public String greeting() {
		HashMap<String, String> parameters = new HashMap<>();
		parameters.put("greeting", "Hola Perrin");
		String params = parseParams(parameters);
		String backendServiceUrl = String.format("http://%s:%d%s/%s?%s", backendServiceHost,
				backendServicePort,prefix,service,params);
		System.out.println("Sending to: " + backendServiceUrl);
		BackendDTO response = null;
		try {
			response = template.getForObject(backendServiceUrl, BackendDTO.class, saying);	
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			response = new BackendDTO();
			response.setGreeting("ERROR!");
			response.setIp("ERROR");
			response.setTime(-1l);
		}
		return response.toString();
	}

	public String parseParams(Map<String, String> parameters) {
		System.out.println("parseParams Starting with: " + parameters);
		StringBuilder sb = new StringBuilder();
		if(parameters!=null) {			
			for(String key:parameters.keySet()) {
				if(sb.length()>0) {
					sb.append(",");
				}
				sb.append(key).append("=").append(parameters.get(key));
			}
		}
		String result = sb.toString();
		System.out.println("parseParams Finished with: " + result);
		return result;
	}

	public String getBackendServiceHost() {
		return backendServiceHost;
	}

	public void setBackendServiceHost(String backendServiceHost) {
		this.backendServiceHost = backendServiceHost;
	}

	public int getBackendServicePort() {
		return backendServicePort;
	}

	public void setBackendServicePort(int backendServicePort) {
		this.backendServicePort = backendServicePort;
	}

	public String getSaying() {
		return saying;
	}

	public void setSaying(String saying) {
		this.saying = saying;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
