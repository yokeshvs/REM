package com.core.domain;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement
public class PatientRecord {

	@SerializedName("Patient_Name")
	private String patientName;
	private String patientId;
	@SerializedName("Initial_Ulcerstage")
	private String initialUlcerStage;
	@SerializedName("Final_Ulcerstage")
	private String finalUlcerStage;
	@SerializedName("MRN_No")
	private String mrnNo;
	@SerializedName("Admit_Date")
	private String admitDate;
	@SerializedName("Discharge_Date")
	private String dischargeDate;
	@SerializedName("Equipment_Used")
	private String deviceUsed;

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getInitialUlcerStage() {
		return initialUlcerStage;
	}

	public void setInitialUlcerStage(String initialUlcerStage) {
		this.initialUlcerStage = initialUlcerStage;
	}

	public String getFinalUlcerStage() {
		return finalUlcerStage;
	}

	public void setFinalUlcerStage(String finalUlcerStage) {
		this.finalUlcerStage = finalUlcerStage;
	}

	public String getMrnNo() {
		return mrnNo;
	}

	public void setMrnNo(String mrnNo) {
		this.mrnNo = mrnNo;
	}

	public String getAdmitDate() {
		return admitDate;
	}

	public void setAdmitDate(String admitDate) {
		this.admitDate = admitDate;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getDeviceUsed() {
		return deviceUsed;
	}

	public void setDeviceUsed(String deviceUsed) {
		this.deviceUsed = deviceUsed;
	}

	@Override
	public String toString() {
		return "PatientRecord [patientName=" + patientName + ", patientId=" + patientId + ", initialUlcerStage="
				+ initialUlcerStage + ", finalUlcerStage=" + finalUlcerStage + ", mrnNo=" + mrnNo + ", admitDate="
				+ admitDate + ", dischargeDate=" + dischargeDate + ", deviceUsed=" + deviceUsed + "]";
	}

}
