package com.core.services.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.ws.rs.Path;
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

	public Map<String, LedgerDevice> getClosestEdge() {
		LOGGER.debug("inside getClosestEdge");
		String deviceInfo = WebServiceUtil.getDeviceInfo();
		LOGGER.debug("FirebaseServiceImpl:getClosestEdge::deviceInfo: " + deviceInfo);
		Map<String, LedgerDevice> ledgerDevices = new HashMap<>();

		if (deviceInfo != null && !deviceInfo.equalsIgnoreCase(REMConstants.NULL)) {
			JsonObject devicesJSON = new JsonParser().parse(deviceInfo).getAsJsonObject();
			JSONObject devices = new JSONObject(devicesJSON.toString());
			String[] deviceIdList = JSONObject.getNames(devices);

			for (int iterator = 0; iterator < deviceIdList.length; iterator++) {
				long latestSignalTime = Long.MIN_VALUE;
				if (devices.get(deviceIdList[iterator]) != null
						&& !devices.get(deviceIdList[iterator]).toString().equalsIgnoreCase("null")) {
					JSONObject currentDevice = (JSONObject) devices.get(deviceIdList[iterator]);
					String[] properties = JSONObject.getNames(currentDevice);
					Integer signalStrength = Integer.MIN_VALUE;
					Integer currentSignalStrength = 0;
					String deviceID = REMConstants.EMPTY_STRING;
					String edgeID = REMConstants.EMPTY_STRING;
					long timeLimit = Long.MAX_VALUE;
					long currentEdgeTimeStamp = Long.MIN_VALUE;
					long currentTime = Calendar.getInstance(TimeZone.getTimeZone("PST")).getTimeInMillis();
					boolean status = false;

					for (int j = 0; j < properties.length; j++) {
						JSONObject currentEdge = (JSONObject) currentDevice.get(properties[j]);
						currentSignalStrength = (Integer) currentEdge.get("distance");
						currentEdgeTimeStamp = (long) currentEdge.get("time");
						if (currentEdgeTimeStamp > latestSignalTime) {
							latestSignalTime = currentEdgeTimeStamp;
						}
					}
					LOGGER.debug("FirebaseServiceImpl:getClosestEdge::latestSignalTime: " + latestSignalTime);
					for (int j = 0; j < properties.length; j++) {
						JSONObject currentEdge = (JSONObject) currentDevice.get(properties[j]);
						currentSignalStrength = (Integer) currentEdge.get("distance");
						currentTime = Calendar.getInstance(TimeZone.getTimeZone("PST")).getTimeInMillis();
						long edgeTime = (long) currentEdge.get("time");
						timeLimit = currentTime - edgeTime;
						LOGGER.debug("FirebaseServiceImpl:getClosestEdge::currentTime: " + currentTime);
						LOGGER.debug("FirebaseServiceImpl:getClosestEdge::timeLimit: " + timeLimit);
						if (latestSignalTime - edgeTime < 5000) {
							if (currentSignalStrength > signalStrength) {
								signalStrength = currentSignalStrength;
								edgeID = properties[j];
								deviceID = currentEdge.get("deviceId").toString();
							}
							if (timeLimit < 90000) {
								status = true;
							} else {
								status = false;
							}
						}
					}
					if (deviceID != null && !deviceID.equalsIgnoreCase(REMConstants.EMPTY_STRING)) {
						LedgerDevice ledgerEntry = new LedgerDevice();
						ledgerEntry.setEdgeID(edgeID);
						ledgerEntry.setSignalStrength(signalStrength);
						ledgerEntry.setStatus(status);
						ledgerEntry.setTime(getCurrentTimeStamp());
						ledgerDevices.put(deviceID, ledgerEntry);
					}
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
		fbDevice.add("deviceId", devicePacket.get("deviceId"));
		fbDevice.add("edgeId", devicePacket.get("edge"));
		fbDevice.add("distance", devicePacket.get("rssi"));
		String json = fbDevice.toString();
		Response response = WebServiceUtil.updateDeviceLocation(apiURL, json);
		if (response != null && response.getStatusInfo().getStatusCode() == 200) {
			return Response.ok().entity("Success").build();
		} else {
			return Response.ok().entity("Failed").build();
		}
	}

	public Response getDevicesConfig() {
		LOGGER.debug("inside getClosestEdge");
		String response = WebServiceUtil.getDevicesConfig();
		if (response != null && !response.equalsIgnoreCase(REMConstants.EMPTY_STRING)) {
			return Response.ok().entity(response).build();
		} else {
			return Response.ok().entity("Failed").build();
		}
	}
}
