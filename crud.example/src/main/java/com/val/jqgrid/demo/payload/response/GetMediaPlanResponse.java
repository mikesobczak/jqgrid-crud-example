package com.val.jqgrid.demo.payload.response;

public class GetMediaPlanResponse {
	
	private Payload payload;
	
	private String exception;
	private Integer returnCode;

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public Integer getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}

}
