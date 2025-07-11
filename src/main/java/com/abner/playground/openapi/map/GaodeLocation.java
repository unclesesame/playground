package com.abner.playground.openapi.map;

import java.util.List;

public class GaodeLocation {
	private String status;
	private String count;
	private String info;
	private List<Geocodes> geocodes;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<Geocodes> getGeocodes() {
		return geocodes;
	}

	public void setGeocodes(List<Geocodes> geocodes) {
		this.geocodes = geocodes;
	}
}
