package com.core.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

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

public class WebServiceUtil {
	private static final Logger LOGGER = LogManager.getLogger("WebServiceUtil");

	public static Response callPOSTService(String apiURL, String json, boolean isLedgerService) {
		Client client = ClientBuilder.newClient();
		LOGGER.debug("WebServiceUtil:callGETService::apiURL: " + apiURL);
		WebTarget resource = client.target(apiURL);
		Response response = resource.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Authorization", getSignInHeaderString(isLedgerService))
				.post(Entity.entity(json, MediaType.APPLICATION_JSON));
		return response;
	}

	public static Response callGETService(String apiURL, String json, boolean isLedgerService) {
		Client client = ClientBuilder.newClient();
		LOGGER.debug("WebServiceUtil:callGETService::apiURL: " + apiURL);
		WebTarget resource = client.target(apiURL);
		Response response = resource.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Authorization", getSignInHeaderString(isLedgerService)).get();
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

	private static String getSignInHeaderString(boolean isLedgerService) {
		String headerString = REMConstants.EMPTY_STRING;
		if (isLedgerService) {
			headerString = "Bearer " + getBearerTokenForLedger();
		} else {
			headerString = "Bearer " + getBearerToken();
		}
		LOGGER.debug("Token: " + headerString);
		return headerString;
	}

	private static String getBearerToken() {
		String credentials = REMConstants.CLIENT_ID_V2 + ":" + REMConstants.CLIENT_SECRET_V2;
		String oauthHeader = "Basic "
				+ Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
		String apiURL = REMConstants.HYPERLEDGER_HOST_V2 + REMConstants.OAUTH2_URL_V2;
		String oauth2 = getOauth2(apiURL, oauthHeader);
		return oauth2;
	}

	private static String getBearerTokenForLedger() {
		String credentials = REMConstants.CLIENT_ID_LEDGER + ":" + REMConstants.CLIENT_SECRET_LEDGER;
		String oauthHeader = "Basic "
				+ Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
		String apiURL = REMConstants.HYPERLEDGER_HOST_LEDGER + REMConstants.OAUTH2_URL_LEDGER;
		String oauth2 = getOauth2(apiURL, oauthHeader);
		return oauth2;
	}

	public static String getDeviceInfo() {
		String outputData = REMConstants.EMPTY_STRING;
		Response response = getFirebaseData(REMConstants.FB_DEVICES);
		if (response != null) {
			outputData = response.readEntity(String.class);
		}
		return outputData;
	}

	public static Response getFirebaseData(String targetURL) {
		Client client = ClientBuilder.newClient();
		WebTarget resource = client.target(targetURL + REMConstants.FB_KEY);
		Response response = resource.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		return response;
	}

	public static Response updateDeviceLocation(String apiURL, String json) {
		Client client = ClientBuilder.newClient();
		WebTarget resource = client.target(apiURL);
		Response response = resource.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(json, MediaType.APPLICATION_JSON));
		return response;
	}
}
