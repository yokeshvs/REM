package com.core.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public interface REMService {

	@Path("/test")
	@GET
	public Response testMethod();
}
