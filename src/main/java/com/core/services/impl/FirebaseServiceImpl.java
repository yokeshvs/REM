package com.core.services.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.core.constants.REMConstants;
import com.core.domain.Device;
import com.core.services.FirebaseService;
import com.core.util.WebServiceUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component("FirebaseServiceImpl")
@Path("/firebase")
public class FirebaseServiceImpl implements FirebaseService {
	private static final Logger LOGGER = LogManager.getLogger("FirebaseServiceImpl");

	public Response registerDevice(Device device) {
		LOGGER.debug("inside registerDevice");
		return null;
	}

	public String getClosestEdge(String apiURL) {
		LOGGER.debug("inside getClosestEdge");
		return WebServiceUtil.callGETFirebaseService(apiURL);
	}

	public Response updateDeviceInfo(String message) {
		JsonObject devicePacket = new JsonParser().parse(message).getAsJsonObject();
		String apiURL = REMConstants.FB_DEVICELOCAITON_FUNC;
		JsonObject fbDevice = new JsonObject();
		fbDevice.add("deviceId", devicePacket.get("deviceID"));
		fbDevice.add("edgeId", devicePacket.get("edgeID"));
		fbDevice.add("distance", devicePacket.get("signalStrength"));
		String json = fbDevice.toString();
		Response response = WebServiceUtil.updateDeviceLocation(apiURL, json);
		if (response != null && response.getStatusInfo().getStatusCode() == 200) {
			return Response.ok().entity("Success").build();
		} else {
			return Response.ok().entity("Failed").build();
		}
	}
}
