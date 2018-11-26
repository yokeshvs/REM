package com.core.domain;

public class Device {
	private Integer deviceId;
	private DeviceDetails deviceDetails;

	public Integer getDeviceID() {
		return deviceId;
	}

	public void setDeviceID(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public DeviceDetails getDeviceDetails() {
		return deviceDetails;
	}

	public void setDeviceDetails(DeviceDetails deviceDetails) {
		this.deviceDetails = deviceDetails;
	}
}
