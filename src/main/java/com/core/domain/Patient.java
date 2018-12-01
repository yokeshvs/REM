package com.core.domain;

public class Patient {
	private Integer patientId;
	private PatientRecord patientRecord;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public PatientRecord getPatientRecord() {
		return patientRecord;
	}

	public void setPatientRecord(PatientRecord patientRecord) {
		this.patientRecord = patientRecord;
	}
}
