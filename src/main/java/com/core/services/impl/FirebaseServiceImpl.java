package com.core.services.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.core.domain.Device;
import com.core.services.FirebaseService;

@Component
@Path("/firebase")
public class FirebaseServiceImpl implements FirebaseService {
	private static final Logger LOGGER = LogManager.getLogger("FirebaseServiceImpl");

	public Response registerDevice(Device device) {
		LOGGER.debug("inside registerDevice");
		return null;
	}
}
