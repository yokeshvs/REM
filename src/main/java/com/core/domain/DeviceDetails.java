package com.core.domain;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement
public class DeviceDetails {

	@SerializedName("Timestamp")
	private String time;

	@SerializedName("EdgeID")
	private String edgeId;

	@SerializedName("DStatus")
	private boolean status;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getEdgeId() {
		return edgeId;
	}

	public void setEdgeId(String edgeId) {
		this.edgeId = edgeId;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
