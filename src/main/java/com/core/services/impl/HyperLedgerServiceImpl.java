package com.core.services.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.core.constants.REMConstants;
import com.core.services.HyperLedgerService;
import com.core.util.WebServiceUtil;

@Component
@Path("/hyperledger")
public class HyperLedgerServiceImpl implements HyperLedgerService {

	private static final Logger LOGGER = LogManager.getLogger("HyperLedgerServiceImpl");

	public Response addDeviceBlock() {
		LOGGER.debug("inside addBlock");
		return Response.ok().entity("Success").build();
	}

	public Response getDeviceBlock(String deviceID) {
		LOGGER.debug("inside getBlock");
		String apiURL = REMConstants.HYPERLEDGER_API_HOST + REMConstants.CHAIN_CODE1_API + "/latest/" + deviceID;
		Response response = WebServiceUtil.callGETService(apiURL, "");
		if (response != null) {
			String outputData = response.readEntity(String.class);
			return Response.ok().entity(outputData).build();
		}
		return Response.ok().entity("Success").build();
	}
}
