package com.abner.playground.jar;

public class RequestParam {
	String cob;
	String scenario;
	String requestName;
	
	public RequestParam(String cob, String scenario, String requestName) {
		super();
		this.cob = cob;
		this.scenario = scenario;
		this.requestName = requestName;
	}
	
	public String getCob() {
		return cob;
	}
	public void setCob(String cob) {
		this.cob = cob;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	@Override
	public String toString() {
		return "RequestParam [cob=" + cob + ", scenario=" + scenario + ", requestName=" + requestName + "]";
	}
	
}
