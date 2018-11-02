package com.core.services.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.core.constants.REMConstants;
import com.core.domain.Device;
import com.core.services.HyperLedgerService;
import com.core.util.WebServiceUtil;
import com.google.gson.Gson;

@Component
@Path("/hyperledger")
public class HyperLedgerServiceImpl implements HyperLedgerService {

	private static final Logger LOGGER = LogManager.getLogger("HyperLedgerServiceImpl");

	public Response addDeviceBlock(Device device) {
		LOGGER.debug("inside addBlock");
		String apiURL = REMConstants.HYPERLEDGER_API_HOST_1 + REMConstants.CHAIN_CODE1_API_1 + "/latest/"
				+ device.getDeviceID();
		String json = new Gson().toJson(device.getDeviceDetails());
		LOGGER.debug("HyperLedgerServiceImpl::addDeviceBlock::json: " + json);
		Response response = WebServiceUtil.callPOSTService(apiURL, json);
		if (response != null && response.getStatusInfo().getStatusCode() == 200) {
			return Response.ok().entity("Success").build();
		} else {
			return Response.ok().entity("Failed").build();
		}

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
