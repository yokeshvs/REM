package com.core.domain;

public class PatientRecord {

	private String patientName;
	private Integer patientId;
	private Integer initialUlcerStage;
	private Integer finalUlcerStage;
	private Integer mrnNo;
	private Integer admitDate;
	private Integer dischargeDate;
	private boolean deviceUsed;

	public boolean isDeviceUsed() {
		return deviceUsed;
	}

	public void setDeviceUsed(boolean deviceUsed) {
		this.deviceUsed = deviceUsed;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getInitialUlcerStage() {
		return initialUlcerStage;
	}

	public void setInitialUlcerStage(Integer initialUlcerStage) {
		this.initialUlcerStage = initialUlcerStage;
	}

	public Integer getFinalUlcerStage() {
		return finalUlcerStage;
	}

	public void setFinalUlcerStage(Integer finalUlcerStage) {
		this.finalUlcerStage = finalUlcerStage;
	}

	public Integer getMrnNo() {
		return mrnNo;
	}

	public void setMrnNo(Integer mrnNo) {
		this.mrnNo = mrnNo;
	}

	public Integer getAdmitDate() {
		return admitDate;
	}

	public void setAdmitDate(Integer admitDate) {
		this.admitDate = admitDate;
	}

	public Integer getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Integer dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	@Override
	public String toString() {
		return "PatientRecord [patientName=" + patientName + ", patientId=" + patientId + ", initialUlcerStage="
				+ initialUlcerStage + ", finalUlcerStage=" + finalUlcerStage + ", mrnNo=" + mrnNo + ", admitDate="
				+ admitDate + ", dischargeDate=" + dischargeDate + ", deviceUsed=" + deviceUsed + "]";
	}

}
