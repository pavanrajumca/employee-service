package com.employee.service.beans;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccessToken {

	@JsonIgnore
	private Map<String, Object> headers;

	private Map<JWTClaimType, Object> payload;

	@JsonIgnore
	private String token;

	public Map<String, Object> getHeaders() {
		return headers;
	}

	public void setHeaders(final Map<String, Object> headers) {
		this.headers = headers;
	}

	public Map<JWTClaimType, Object> getPayload() {
		return payload;
	}

	public void setPayload(final Map<JWTClaimType, Object> payload) {
		this.payload = payload;
	}

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

}
