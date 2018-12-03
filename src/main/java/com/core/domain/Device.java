package com.core.domain;

public class Device {
	private String deviceId;
	private DeviceDetails deviceDetails;

	public String getDeviceID() {
		return deviceId;
	}

	public void setDeviceID(String deviceId) {
		this.deviceId = deviceId;
	}

	public DeviceDetails getDeviceDetails() {
		return deviceDetails;
	}

	public void setDeviceDetails(DeviceDetails deviceDetails) {
		this.deviceDetails = deviceDetails;
	}
}
