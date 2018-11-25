package com.core.constants;

public interface REMConstants {

	public final static int QOS = 2;
	public final static String EMPTY_STRING = "";
	public final static String NULL = "null";
	public final static String DEVICE_TOPIC = "911d1824-d6ae-432b-b041-f1b473121a2e/REM/deviceInfo";
	public final static String DEVICE_TOPIC_1 = "EE712681-0741-483F-A804-B5B102166045";
	public final static String CLIENT_ID = "sb-932233bf-39fd-467e-bb43-b000b2802e49!b6846|na-420adfc9-f96e-4090-a650-0386988b67e0!b1836";
	public final static String CLIENT_SECRET = "IFmCaBCFTMB/71Eaa61DZyKgvOI=";
	public final static String HYPERLEDGER_HOST = "https://p2000592635trial.authentication.eu10.hana.ondemand.com";
	public final static String HYPERLEDGER_API_HOST = "https://hyperledger-fabric.cfapps.eu10.hana.ondemand.com";
	public final static String OAUTH2_URL = "/oauth/token?grant_type=client_credentials";
	public final static String CHAIN_CODE1_API = "/api/v1/chaincodes/a00b8172-eed1-439e-8ce2-83ca2357caef-com-sap-icn-blockchain-example-helloUniverse";

	// Different Channel
	public final static String CLIENT_ID_1 = "sb-f6ae61cf-a20d-43bd-be39-73cde7023406!b1341|na-3a01f1e2-bc33-4e12-86a2-ffffaea79918!b33";
	public final static String CLIENT_SECRET_1 = "hBfLBqnhSS9psUFoMyr2fB17Pak=";
	public final static String HYPERLEDGER_HOST_1 = "https://p1709935996trial.authentication.us10.hana.ondemand.com";
	public final static String HYPERLEDGER_API_HOST_1 = "https://hyperledger-fabric.cfapps.us10.hana.ondemand.com/api/v1";
	public final static String OAUTH2_URL_1 = "/oauth/token?grant_type=client_credentials";
	public final static String CHAIN_CODE1_API_1 = "/api/v1/chaincodes/04b992d0-3921-4873-839a-b792a739a2ea-com-sap-icn-blockchain-example-helloUniverse";

	// Firebase Constants
	public final static String FB_KEY = "?key=AIzaSyAHyoI7iGdo8vr_UhfUKCylkknFqAFfW_w";
	public final static String FB_DEVICES = "https://uihealth-rem.firebaseio.com/devices.json";
	public final static String FB_DEVICELOCAITON_FUNC = "https://us-central1-uihealth-rem.cloudfunctions.net/deviceLocation"
			+ FB_KEY;

}
