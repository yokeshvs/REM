package com.core.services.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.core.constants.REMConstants;
import com.core.domain.Device;
import com.core.domain.LedgerDevice;
import com.core.services.FirebaseService;
import com.core.util.WebServiceUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component("FirebaseServiceImpl")
@Path("/firebase")
public class FirebaseServiceImpl implements FirebaseService {
	private static final Logger LOGGER = LogManager.getLogger("FirebaseServiceImpl");
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Response registerDevice(Device device) {
		LOGGER.debug("inside registerDevice");
		return null;
	}

	private static String getCurrentTimeStamp() {
		return format.format(new Date());
	}

	public Map<Integer, LedgerDevice> getClosestEdge() {
		LOGGER.debug("inside getClosestEdge");
		String deviceInfo = WebServiceUtil.getDeviceInfo();
		LOGGER.debug("FirebaseServiceImpl:getClosestEdge::deviceInfo: " + deviceInfo);
		JsonArray devices = new JsonParser().parse(deviceInfo).getAsJsonArray();
		Map<Integer, LedgerDevice> ledgerDevices = new HashMap<>(); 

		for (int i = 0; i < devices.size(); i++) {
			if (devices.get(i) != null && !devices.get(i).toString().equalsIgnoreCase("null")) {
				JsonObject devicesData = devices.get(i).getAsJsonObject();
				JSONObject currentDevice = new JSONObject(devicesData.toString());
				String[] properties = JSONObject.getNames(currentDevice);
				Integer signalStrength = Integer.MIN_VALUE;
				Integer currentSignalStrength = 0;
				Integer deviceID = 0;
				String edgeID = REMConstants.EMPTY_STRING;
				long timeLimit = Long.MAX_VALUE;
				long currentTime = Calendar.getInstance(TimeZone.getTimeZone("PST")).getTimeInMillis();
				boolean status = false;
				for (int j = 0; j < properties.length; j++) {
					JSONObject currentEdge = (JSONObject) currentDevice.get(properties[j]);
					currentSignalStrength = (Integer) currentEdge.get("distance");
					currentTime = Calendar.getInstance(TimeZone.getTimeZone("PST")).getTimeInMillis();
					timeLimit = currentTime - (long) currentEdge.get("time");
					LOGGER.debug("FirebaseServiceImpl:getClosestEdge::currentTime: " + currentTime);
					LOGGER.debug("FirebaseServiceImpl:getClosestEdge::timeLimit: " + timeLimit);
					if (currentSignalStrength > signalStrength) {
						signalStrength = currentSignalStrength;
						edgeID = properties[j];
						deviceID = (Integer) currentEdge.get("deviceId");
					}
					if (timeLimit < 90000) {
						status = true;
					} else {
						status = false;
					}
				}
				if (deviceID != null && deviceID > 0) {
					LedgerDevice ledgerEntry = new LedgerDevice();
					ledgerEntry.setEdgeID(edgeID);
					ledgerEntry.setSignalStrength(signalStrength);
					ledgerEntry.setStatus(status);
					ledgerEntry.setTime(getCurrentTimeStamp());
					ledgerDevices.put(deviceID, ledgerEntry);
				}
			}
		}
		LOGGER.debug("FirebaseServiceImpl:getClosestEdge::ledgerDevices: " + ledgerDevices.toString());
		return ledgerDevices;
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
