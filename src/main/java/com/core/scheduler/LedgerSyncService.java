package com.core.scheduler;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.core.domain.Device;
import com.core.domain.DeviceDetails;
import com.core.domain.LedgerDevice;
import com.core.services.FirebaseService;
import com.core.services.HyperLedgerService;

@Component("ledgerSyncService")
public class LedgerSyncService {

	@Autowired(required = true)
	@Qualifier("FirebaseServiceImpl")
	private FirebaseService firebaseService;

	@Autowired(required = true)
	@Qualifier("HyperLedgerServiceImpl")
	private HyperLedgerService ledgerService;

	private static final Logger LOGGER = LogManager.getLogger("LedgerSyncService");

	public void syncLedger() {
		Map<String, LedgerDevice> ledgerDevices = firebaseService.getClosestEdge();
		LOGGER.debug("LedgerSyncService::syncLedger::ledgerDevices: " + ledgerDevices);
		for (String deviceId : ledgerDevices.keySet()) {
			LedgerDevice currentDevice = ledgerDevices.get(deviceId);
			Device device = new Device();
			device.setDeviceID(deviceId);
			DeviceDetails deviceDetails = new DeviceDetails();
			deviceDetails.setEdgeId(currentDevice.getEdgeID());
			deviceDetails.setTime(currentDevice.getTime());
			deviceDetails.setStatus(currentDevice.isStatus());
			device.setDeviceDetails(deviceDetails);
			ledgerService.createUpdateDevice(device);
		}
	}
}
