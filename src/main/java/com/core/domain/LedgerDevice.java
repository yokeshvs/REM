package com.core.domain;

public class LedgerDevice {
	private String edgeID;
	private Integer signalStrength;
	private boolean status;
	private String time;
	public String getEdgeID() {
		return edgeID;
	}
	public void setEdgeID(String edgeID) {
		this.edgeID = edgeID;
	}
	public Integer getSignalStrength() {
		return signalStrength;
	}
	public void setSignalStrength(Integer signalStrength) {
		this.signalStrength = signalStrength;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
