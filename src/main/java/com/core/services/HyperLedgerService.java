package com.core.services;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public interface HyperLedgerService {

	@Path("/device")
	@POST
	public Response addDeviceBlock();
	
	@Path("/device")
	@GET
	public Response getDeviceBlock(@QueryParam("device") String deviceID);
}
