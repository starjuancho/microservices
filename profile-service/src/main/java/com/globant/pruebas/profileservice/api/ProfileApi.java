package com.globant.pruebas.profileservice.api;

import java.security.NoSuchAlgorithmException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.pruebas.profileservice.CryptoUtils;
import com.globant.pruebas.profileservice.dao.Dao;
import com.globant.pruebas.profileservice.message.Publisher;
import com.globant.pruebas.profileservice.model.Profile;
import com.globant.pruebas.profileservice.model.Status;
import com.globant.pruebas.profileservice.model.User;

@Component
@Path("/user")
public class ProfileApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileApi.class); 

	@Autowired
	private Dao<Profile> profileDao;

	@Autowired
	private CryptoUtils cryptoUtils;

	@Autowired
	private Dao<User> userDao;

	@Autowired
	private Publisher publisher;
	
	@Autowired
	ObjectMapper objectMapper;

	@GET
	public Response heartBeat() {
		return Response.ok().build();
	}

	@Path("/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBasicData(@PathParam("userId") String userId) {
		LOGGER.debug("Call to getUserBasicData: %s%n", userId);
		User user = userDao.getById(userId);
		return Response.ok(user, MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		LOGGER.debug("Starting createUser: %s%n", user);

		if (user==null) {
			return Response.status(422).entity("Invalid user: null").build();
		}
		if(user.getUsername().isEmpty()) {
			return Response.status(422).entity("Invalid user: null").build();
		} 
		try {
			user.setUserID(cryptoUtils.Hash256(user.getUsername()));
		} catch (NoSuchAlgorithmException e) {
			return Response.status(500).build();
		}
		user.setStatus(new Status((short) 1, "new", "Just Created"));
		String jsonUser = null;
		try {
			jsonUser = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			return Response.status(500).build();
		}
		Long key = System.currentTimeMillis();
		publisher.sendMessage(key,jsonUser);
		LOGGER.debug("FInished createUser: %s%n", user);
		return Response.status(200).entity(user).build();
	}

	@Path("/{userId}/profile")
	@GET
	@Produces("application/json")
	public Response getProfiel(@PathParam("userId") String userId) {
		LOGGER.debug("Call to getProfiel: %s%n", userId);
		Profile profile = profileDao.getById(userId);
		return Response.ok(profile, MediaType.APPLICATION_JSON).build();
	}

}
