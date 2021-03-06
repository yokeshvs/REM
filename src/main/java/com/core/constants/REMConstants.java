package com.core.constants;

public interface REMConstants {

	public final static int QOS = 2;
	public final static String EMPTY_STRING = "";
	public final static String NULL = "null";
	public final static String DEVICE_TOPIC = "911d1824-d6ae-432b-b041-f1b473121a2e/REM/deviceInfo";
	public final static String DEVICE_TOPIC_1 = "EE712681-0741-483F-A804-B5B102166045";
	public final static String DEVICE_TOPIC_2 = "wcl/uic";

	// HyperLedger Constants
	public final static String CLIENT_ID = "sb-0b3866a0-7b66-4881-9bca-6e8f41c35db2!b6846|na-420adfc9-f96e-4090-a650-0386988b67e0!b1836";
	public final static String CLIENT_SECRET = "V6A1AT924dqjygfqoJQxtYiB0AE=";
	public final static String HYPERLEDGER_HOST = "https://p2000592635trial.authentication.eu10.hana.ondemand.com";
	public final static String HYPERLEDGER_API_HOST = "https://hyperledger-fabric.cfapps.eu10.hana.ondemand.com";
	public final static String OAUTH2_URL = "/oauth/token?grant_type=client_credentials";
	public final static String CHAIN_CODE1_API = "/api/v1/chaincodes/08fc88c6-9d53-4aa5-8d52-66e0225d8a2c-com-sap-icn-blockchain-example-helloUniverse/35";

	// HyperLedger V2 Constants
	// Equipment Tracking
	public final static String CLIENT_ID_V2 = "sb-67a1eea0-5aca-4653-bed8-3dbc4e4dc3d8!b8618|na-420adfc9-f96e-4090-a650-0386988b67e0!b1836";
	public final static String CLIENT_SECRET_V2 = "vwuSzi2jImETxzw0bjpOMzM9qB0=";
	public final static String HYPERLEDGER_HOST_V2 = "https://p2000718099trial.authentication.eu10.hana.ondemand.com";
	public final static String HYPERLEDGER_API_HOST_V2 = "https://hyperledger-fabric.cfapps.eu10.hana.ondemand.com";
	public final static String OAUTH2_URL_V2 = "/oauth/token?grant_type=client_credentials";
	public final static String CHAIN_CODE_API_V2 = "/api/v1/chaincodes/a775a3ce-1a07-401e-83f0-8ea7bed00b36-com-sap-icn-blockchain-example-helloUniverse/52";

	// Patient Ledger
	public final static String CLIENT_ID_LEDGER = "sb-3045382c-b10c-4683-b8cf-01989e2eaa05!b7808|na-420adfc9-f96e-4090-a650-0386988b67e0!b1836";
	public final static String CLIENT_SECRET_LEDGER = "uY58aLfuqS7cafZwKnTc8jQpEAM=";
	public final static String HYPERLEDGER_HOST_LEDGER = "https://p2000576141trial.authentication.eu10.hana.ondemand.com";
	public final static String HYPERLEDGER_API_HOST_LEDGER = "https://hyperledger-fabric.cfapps.eu10.hana.ondemand.com";
	public final static String OAUTH2_URL_LEDGER = "/oauth/token?grant_type=client_credentials";
	public final static String CHAIN_CODE_API_LEDGER = "/api/v1/chaincodes/9e92356b-b247-4f3e-98a3-914aebfcb64e-com-sap-icn-blockchain-example-helloUniverse/25";

	// Firebase Constants
	public final static String FB_KEY = "?key=AIzaSyAHyoI7iGdo8vr_UhfUKCylkknFqAFfW_w";
	public final static String FB_DEVICES = "https://uihealth-rem.firebaseio.com/devices.json";
	public final static String FB_DEVICES_CONFIG = "https://uihealth-rem.firebaseio.com/devicesConfig.json";
	public final static String FB_DEVICELOCAITON_FUNC = "https://us-central1-uihealth-rem.cloudfunctions.net/deviceLocation"
			+ FB_KEY;

}
