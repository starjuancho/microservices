package com.globant.pruebas.profileservice.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResponseExtractor;

import com.globant.pruebas.profileservice.CryptoUtils;
import com.globant.pruebas.profileservice.model.Profile;
import com.globant.pruebas.profileservice.model.User;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestProfileApi {

	private static final String BASE_URI = "/user";
	private static final String EXISTING_USER = "starjuancho";

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int port;

	private final StatusExtractor statusExtractor = new StatusExtractor();

	@Test
	public void testHeartbeat() {
		System.out.format("Starting testHeartbeat%n");
		HttpStatus result = restTemplate.execute(BASE_URI, HttpMethod.GET, null, (response) -> {
			return response.getStatusCode();
		});
		assertTrue(HttpStatus.OK.equals(result));
		System.out.format("Finished testHeartbeat%n");
	}

	@Test
	public void testGetUserBasicData() {
		System.out.format("Starting testGetUserBasicData%n");
		User user = restTemplate.getForObject(BASE_URI + "/" + EXISTING_USER, User.class);
		assertNotNull(user);
		System.out.format("Finished testGetUserBasicData%s%n", user);
	}

	@Test
	public void testGetProfile() {
		System.out.format("Starting testGetProfile%n");
		Profile profile = restTemplate.getForObject(BASE_URI + "/" + EXISTING_USER + "/profile", Profile.class);
		assertNotNull(profile);
		System.out.format("Finished testGetProfile%s%n", profile);
	}

	@Test
	public void testInsertUser() {
		System.out.format("Starting testInsertUser%n");
		User newUser = new User();
		newUser.setUsername("Juanelo");
		User user = restTemplate.postForObject(BASE_URI, newUser, User.class);
		assertNotNull(user);
		try {
			String hashed = cryptoUtils.Hash256(user.getUsername());
			assertEquals("userId must be equals to hased one", hashed, user.getUserID());
		} catch (NoSuchAlgorithmException e) {
			fail("Can't has username. NoSuchAlgorithmException: " + e.getMessage());
		}
		System.out.format("Finished testInsertUser%s%n", user);
	}

	@Test
	public void testInsertUserNull() {
		System.out.format("Starting testInsertUserNull%n");
		HttpStatus result = restTemplate.execute(BASE_URI, HttpMethod.POST, (request) -> {
			request.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		}, statusExtractor);

		assertEquals("Expected invalidError", HttpStatus.UNPROCESSABLE_ENTITY, result);
		System.out.format("Finished testInsertUserNull%n");
	}

	@Test
	public void testInsertUserNameEmpty() {
		System.out.format("Starting testInsertUserNull%n");
		HttpStatus result = restTemplate.execute(BASE_URI, HttpMethod.POST, (request) -> {
			String body = "{\"username\": \"\"}";
			request.getBody().write(body.getBytes());
			request.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		}, statusExtractor);

		assertEquals("Expected invalidError", HttpStatus.UNPROCESSABLE_ENTITY, result);
		System.out.format("Finished testInsertUserNull%n");
	}

	@Test
	public void testInsertUserNameNull() {
		System.out.format("Starting testInsertUserNull%n");
		HttpStatus result = restTemplate.execute(BASE_URI, HttpMethod.POST, (request) -> {
			String body = "";
			request.getBody().write(body.getBytes());
			request.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		}, statusExtractor);

		assertEquals("Expected invalidError", HttpStatus.UNPROCESSABLE_ENTITY, result);
		System.out.format("Finished testInsertUserNull%n");
	}

	 @MockBean
	 private CryptoUtils cryptoUtils;

	@Test
	public void testNoSuchAlgorithmException() {
		System.out.format("Starting testInsertUserNull%n");
		try {
			given(this.cryptoUtils.Hash256(anyString())).willThrow(NoSuchAlgorithmException.class);
		} catch (java.security.NoSuchAlgorithmException e) {
			assertTrue(e instanceof java.security.NoSuchAlgorithmException);
		}
		HttpStatus result = restTemplate.execute(BASE_URI, HttpMethod.POST, (request) -> {
			String body = "{\"username\": \"juanelo\"}";
			request.getBody().write(body.getBytes());
			request.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		}, statusExtractor);

		assertEquals("Expected internalError", HttpStatus.INTERNAL_SERVER_ERROR, result);
		System.out.format("Finished testInsertUserNull%n");
		
	}

	private static class StatusExtractor implements ResponseExtractor<HttpStatus> {

		@Override
		public HttpStatus extractData(ClientHttpResponse response) throws IOException {
			return response.getStatusCode();
		}

	}
}
