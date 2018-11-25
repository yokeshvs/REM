package com.core.services.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.core.constants.REMConstants;
import com.core.domain.Device;
import com.core.services.FirebaseService;
import com.core.util.WebServiceUtil;
import com.google.gson.JsonArray;
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

	public Response getClosestEdge() {
		LOGGER.debug("inside getClosestEdge");
		String deviceInfo = WebServiceUtil.getDeviceInfo();
		LOGGER.debug("FirebaseServiceImpl:getClosestEdge::deviceInfo: " + deviceInfo);
		JsonArray devices = new JsonParser().parse(deviceInfo).getAsJsonArray();
		JSONObject ledgerDevices = new JSONObject();
		for (int i = 0; i < devices.size(); i++) {
			if (devices.get(i) != null && !devices.get(i).toString().equalsIgnoreCase("null")) {
				JsonObject devicesData = devices.get(i).getAsJsonObject();
				JSONObject currentDevice = new JSONObject(devicesData.toString());
				String[] properties = JSONObject.getNames(currentDevice);
				Integer signalStrength = Integer.MIN_VALUE;
				Integer currentSignalStrength = 0;
				String deviceID = REMConstants.EMPTY_STRING;
				String edgeID = REMConstants.EMPTY_STRING;
				for (int j = 0; j < properties.length; j++) {
					JSONObject currentEdge = (JSONObject)currentDevice.get(properties[j]);
					currentSignalStrength = (Integer) currentEdge.get("distance");
					if(currentSignalStrength > signalStrength) {
						signalStrength = currentSignalStrength;
						edgeID = properties[j];
						deviceID = currentEdge.get("deviceId").toString();
					}
				}
				JSONObject ledgerEntry = new JSONObject();
				ledgerEntry.put("edgeId", edgeID);
				ledgerEntry.put("signalStrength", signalStrength);
				ledgerDevices.put(deviceID, ledgerEntry);
			}
		}
		LOGGER.debug("FirebaseServiceImpl:getClosestEdge::ledgerDevices: " + ledgerDevices.toString());
		return Response.ok().entity(ledgerDevices.toString()).build();
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
