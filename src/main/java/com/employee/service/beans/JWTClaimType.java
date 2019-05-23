package com.employee.service.beans;

public enum JWTClaimType {

	ISSUER("iss"), SUBJECT("sub"), AUDIENCE("aud"), EXPIRATION("exp"), NOT_BEFORE("nbf"), ISSUED_AT("iat"), ID("jti"), EXPIRY_DT("EXPIRY_DT"), PARTY_TYPE("party_type"), CLIENT_ID("client_id"), CLIENT_TYPE("client_type"), ACCESS("access"), SCOPE(
			"scope"), CIN_NUMBER("cin"), JWT_TYPE("typ"), JWT_VALUE("JWT"), CLIENT_NAME("client_name"), ACCESS_TOKEN("access_token"), GRANT_TYPE("grant_type");

	private String key;

	private JWTClaimType(final String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return key;
	}

	public static JWTClaimType fromString(final String text) {
		for (final JWTClaimType claimType : JWTClaimType.values()) {
			if (claimType.key.equalsIgnoreCase(text)) {
				return claimType;
			}
		}
		return null;
	}
}
