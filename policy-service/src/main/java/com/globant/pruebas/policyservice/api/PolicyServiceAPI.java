package com.globant.pruebas.policyservice.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.globant.pruebas.policyservice.dao.Dao;
import com.globant.pruebas.policyservice.model.Policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Path("/policy")
public class PolicyServiceAPI {

	private static final Logger LOGGER = LoggerFactory.getLogger(PolicyServiceAPI.class);

	@Autowired
	Dao<Policy> policyDao;

	@GET
	public Response heartBeat(@Context HttpHeaders headers) {
		LOGGER.debug("Successful heartBeat from "+headers.getRequestHeader(HttpHeaders.HOST).get(0));
		return Response.ok().build();
	}

	@GET
	@Path("/{policyNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPolicyByNumber(@PathParam("policyNumber")String policyNumber) {
		LOGGER.debug("Starts getPolicyByNumber: " + policyNumber);

		Policy policy = policyDao.getById(policyNumber);
		Response response = null;
		if (policy == null) {
			response = Response.status(HttpStatus.NOT_FOUND.value()).build();
		} else {
			response = Response.ok().entity(policy).build();
		}

		LOGGER.debug("Finishes getPolicyByNumber: " + policyNumber + ": " +response );
		return response;
	}

}
