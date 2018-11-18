package com.core.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.core.constants.REMConstants;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

public class WebServiceUtil {
	private static final Logger LOGGER = LogManager.getLogger("WebServiceUtil");

	public static Response callPOSTService(String apiURL, String json) {
		Client client = ClientBuilder.newClient();
		LOGGER.debug("WebServiceUtil:callGETService::apiURL: " + apiURL);
		WebTarget resource = client.target(apiURL);
		Response response = resource.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Authorization", getSignInHeaderString()).post(Entity.entity(json, MediaType.APPLICATION_JSON));
		return response;
	}

	public static Response callGETService(String apiURL, String json) {
		Client client = ClientBuilder.newClient();
		LOGGER.debug("WebServiceUtil:callGETService::apiURL: " + apiURL);
		WebTarget resource = client.target(apiURL);
		Response response = resource.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Authorization", getSignInHeaderString()).get();
		return response;
	}

	public static String getOauth2(String apiURL, String header) {
		Client client = ClientBuilder.newClient();
		WebTarget resource = client.target(apiURL);
		String oauth2Header = REMConstants.EMPTY_STRING;
		Response response = resource.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Authorization", header).get();
		if (response != null) {
			String outputData = response.readEntity(String.class);
			JsonObject jsonObj = new JsonParser().parse(outputData).getAsJsonObject();
			oauth2Header = jsonObj.get("access_token").getAsString();
		}
		return oauth2Header;
	}

	private static String getSignInHeaderString() {
		String headerString = "Bearer " + getBearerToken();
		LOGGER.debug("Token: " + headerString);
		return headerString;
	}

	private static String getBearerToken() {
		String credentials = REMConstants.CLIENT_ID + ":" + REMConstants.CLIENT_SECRET;
		String oauthHeader = "Basic "
				+ Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
		String apiURL = REMConstants.HYPERLEDGER_HOST + REMConstants.OAUTH2_URL;
		String oauth2 = getOauth2(apiURL, oauthHeader);
		return oauth2;
	}

	public static String callGETFirebaseService(String apiURL) {
		Client client = ClientBuilder.newClient();
		double distance = Integer.MAX_VALUE;
		String edgeValue = "";
		LOGGER.debug("WebServiceUtil:callGETFirebaseService::apiURL: " + apiURL);
		WebTarget resource = client.target(apiURL + "?key=AIzaSyAHyoI7iGdo8vr_UhfUKCylkknFqAFfW_w");
		Response response = resource.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		if (response != null) {
			String outputData = response.readEntity(String.class);
			JsonObject jsonObj = new JsonParser().parse(outputData).getAsJsonObject();
			LOGGER.debug("WebServiceUtil:callGETFirebaseService::jsonObj: " + jsonObj);
			for (Map.Entry<String, JsonElement> entry : jsonObj.entrySet()) {
				LOGGER.debug("WebServiceUtil:callGETFirebaseService::entryKey: " + entry.getKey());
				LOGGER.debug("WebServiceUtil:callGETFirebaseService::entryValue: " + entry.getValue());
				JsonObject edgeObj = entry.getValue().getAsJsonObject();
				double currentDistance = edgeObj.get("distance").getAsDouble();
				LOGGER.debug("WebServiceUtil:callGETFirebaseService::currentDistance: " + currentDistance);
				if (currentDistance < distance) {
					distance = currentDistance;
					edgeValue = entry.getKey().toString();
				}
				LOGGER.debug("WebServiceUtil:callGETFirebaseService::distance: " + distance);
			}
			LOGGER.debug("WebServiceUtil:callGETFirebaseService::edgeValue: " + edgeValue);
		}
		return edgeValue;
	}

	public static Response updateDeviceLocation(String apiURL, String json) {
		Client client = ClientBuilder.newClient();
		LOGGER.debug("WebServiceUtil:updateDeviceLocation::apiURL: " + apiURL);
		LOGGER.debug("WebServiceUtil:updateDeviceLocation::json: " + json);
		WebTarget resource = client.target(apiURL);
		Response response = resource.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(json, MediaType.APPLICATION_JSON));
		return response;
	}
}
