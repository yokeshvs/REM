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

@Component("HyperLedgerServiceImpl")
@Path("/hyperledger")
public class HyperLedgerServiceImpl implements HyperLedgerService {

	private static final Logger LOGGER = LogManager.getLogger("HyperLedgerServiceImpl");

	public Response createUpdateDevice(Device device) {
		LOGGER.debug("inside addBlock");
		String apiURL = REMConstants.HYPERLEDGER_API_HOST + REMConstants.CHAIN_CODE1_API + "/" + device.getDeviceID();
		String json = new Gson().toJson(device.getDeviceDetails());
		LOGGER.debug("HyperLedgerServiceImpl::addDeviceBlock::json: " + json);
		Response response = WebServiceUtil.callPOSTService(apiURL, json);
		if (response != null && response.getStatusInfo().getStatusCode() == 201) {
			return Response.ok().entity("Success").build();
		} else {
			return Response.ok().entity("Failed").build();
		}
	}

	public Response getDeviceBlock(String deviceID) {
		LOGGER.debug("inside getDeviceBlock");
		String apiURL = REMConstants.HYPERLEDGER_API_HOST + REMConstants.CHAIN_CODE1_API + "/" + deviceID + "/read";
		Response response = WebServiceUtil.callGETService(apiURL, "");
		if (response != null) {
			String outputData = response.readEntity(String.class);
			return Response.ok().entity(outputData).build();
		}
		return Response.ok().entity("Success").build();
	}

	public Response getDeviceHistory(String deviceID) {
		LOGGER.debug("inside getDeviceHistory");
		String apiURL = REMConstants.HYPERLEDGER_API_HOST + REMConstants.CHAIN_CODE1_API + "/" + deviceID + "/history";
		Response response = WebServiceUtil.callGETService(apiURL, "");
		if (response != null) {
			String outputData = response.readEntity(String.class);
			return Response.ok().entity(outputData).build();
		}
		return Response.ok().entity("Success").build();
	}
}
