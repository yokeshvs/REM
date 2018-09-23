package com.core.services.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.core.services.REMService;

@Component
@Path("/REMService")
public class REMServiceImpl implements REMService {

	private static final Logger LOGGER = LogManager.getLogger("REMServiceImpl");
	
	public Response testMethod() {
		LOGGER.debug("inside test");
		return Response.ok().entity("Success").build();
	}
}
