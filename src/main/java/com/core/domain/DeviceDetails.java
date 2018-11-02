package com.core.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeviceDetails {
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
