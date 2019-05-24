package com.employee.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employee.service.beans.AccessToken;
import com.employee.service.beans.JWTClaimType;
import com.employee.service.dao.EmployeeDao;
import com.employee.service.dto.Employee;
import com.employee.service.request.EmployeeRequest;
import com.employee.service.response.EmployeeRegisterResponse;
import com.employee.service.validator.AccessTokenValidator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component("empService")
public class EmployeeService {

	@Autowired
	private AccessTokenValidator accessTokenValidator;
	@Autowired
	private EmployeeDao employeeDao;

	private final String clientId = "clientId";
	private final String secret = "secret";

	public EmployeeRegisterResponse getEmployee(final EmployeeRequest request, final String accessToken) {
		final AccessToken token = accessTokenValidator.validateAccessToken(accessToken, secret);

		if (!StringUtils.equalsIgnoreCase(clientId, token.getPayload().get(JWTClaimType.CLIENT_ID).toString())) {
			throw new RuntimeException("Partner mismatch");
		}

		final Employee employee = new Employee();
		employee.setFirstName(request.getFirstName());
		employee.setLastName(request.getLastName());
		employee.setGender(request.getGender().name());
		employee.setEmail(request.getEmail());
		employee.setMobileNumber(request.getMobile());
		EmployeeRegisterResponse response = employeeDao.findEmployee(employee);
		if (null != response) {
			return response;
		} else {
			response = employeeDao.persist(employee);
		}
		return response;
	}

	public String generateAcessToken(final String partnerAccess) {
		validatePartnerSecret(partnerAccess);

		final Claims claims = Jwts.claims();
		populateDefaultValues(claims);
		return generateAccessToken(claims);
	}

	private void validatePartnerSecret(final String partnerAccess) {
		final String x509CertificateClob = "-----BEGIN CERTIFICATE-----\r\n"
				+ " MIIEsTCCA5mgAwIBAgITWgAAE70CVtZvkZzHRwAAAAATvTANBgkqhkiG9w0BAQsF\r\n"
				+ " ADAcMRowGAYDVQQDExFEQlNCYW5rLVNHLVN1Yi1DQTAeFw0xNjA2MjIwOTQwMTZa\r\n"
				+ " Fw0yNjA2MjIwOTUwMTZaMHkxCzAJBgNVBAYTAlNHMQwwCgYDVQQIEwNVQVQxDDAK\r\n"
				+ " BgNVBAcTA0FQUDEQMA4GA1UECgwHVCZPIENCRzEZMBcGA1UECxMQQ0FQSSBBUEkg\r\n"
				+ " TWFuYWdlcjEhMB8GA1UEAxMYeDAxc2NhcGlndzF6LnVhdC5kYnMuY29tMIIBIjAN\r\n"
				+ " BgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4WusXb3ktsTuH2oRFg+eBwYeQ5aN\r\n"
				+ " tzgO+dZ54XUhGbNbZv/Tx3PHvnpKzyPJ2FyifjJ9rR8ynkhMxzB1I9JTp8viRb/9\r\n"
				+ " Bn5Na1xYTTrCSHRmG9Ha67Biy44dO/5wvpoZ0SHoxS97I6niZrfWy2cnpztsG+Bh\r\n"
				+ " aqGmmu+VEbeh1dN6RrK4tiS7vWA+7AlVtM2lTH52468tQQSXSdq3kLq8vauqifwP\r\n"
				+ " 5QE4SPDLU+eLDKJVM/3qw6+UIymwGznxv7aKJV0ADsAoYQzRXLTtlhYkV28/qvxe\r\n"
				+ " SMLUdZFDWZe/qfNi3rJB3t26gtzSBD9tZto8Sk8fyEfejOF+LHkpoAnmxQIDAQAB\r\n"
				+ " o4IBjTCCAYkwHQYDVR0OBBYEFJozp3M8mZrIWquEjMBlbggKpXKGMB8GA1UdIwQY\r\n"
				+ " MBaAFHzIuiL8Gw8Zu6rPmZ4MNZexMXKfMIIBNwYDVR0fBIIBLjCCASowggEmoIIB\r\n"
				+ " IqCCAR6GR2h0dHA6Ly9XMDFHMUJOS0FQUDAxMDEuUkVHMS4xQkFOSy5EQlMuQ09N\r\n"
				+ " L0NSTERpc3QvREJTQmFuay1TRy1TdWItQ0EuY3JshkdodHRwOi8vVzAzRzFCTktB\r\n"
				+ " UFAwMzAxLlJFRzMuMUJBTksuREJTLkNPTS9DUkxEaXN0L0RCU0JhbmstU0ctU3Vi\r\n"
				+ " LUNBLmNybIZKaHR0cDovL1cwMVMxQk5LQVBQMDEwMS5SRUcxLlVBVDFCQU5LLkRC\r\n"
				+ " Uy5DT00vQ1JMRGlzdC9EQlNCYW5rLVNHLVN1Yi1DQS5jcmyGPmh0dHA6Ly9XMDFH\r\n"
				+ " SU1TTVNDQTFBLlNHUC5EQlMuQ09NL0NSTERpc3QvREJTQmFuay1TRy1TdWItQ0Eu\r\n"
				+ " Y3JsMAwGA1UdEwEB/wQCMAAwDQYJKoZIhvcNAQELBQADggEBAKM51Hhr35hhLDxV\r\n"
				+ " qS5rn1vI/Z/zjKXO328b3WK3Pp0GDFc3sf7KzOkuvr8HG3CpjkxAV9kxnPTQyzju\r\n"
				+ " eW2q/4dj/YCWiGmjR35BNo0Xjt41H4wY6rA1hzgb1XddBF6VBl2+4BDZSKjWrUcc\r\n"
				+ " 9qCB0gjcnWmZMbRCYYOfV8bKLasNMsfX2jSsakt3M6kw0rILAFHN0Cptzutml76k\r\n"
				+ " XMsrP/jdEGXy+jJ/NCPS4F+jNDGV7ePrz1RNBLW8VjW31qlBlykRSrR38YfycP8S\r\n"
				+ " Q/PfQhEG/1Ovtr01TVrd3KCzHwvP9GOw2vcxPbZVMgdVIpYcCLKjjDjLb48Nfm09\r\n" + " +ItJbEA=\r\n"
				+ " -----END CERTIFICATE-----";
		final X509Certificate cert = getX509CertfromBlob(x509CertificateClob.getBytes());
		try {
			cert.checkValidity();
		} catch (CertificateExpiredException | CertificateNotYetValidException e) {
			throw new RuntimeException("partner certificate expired");
		}
	}

	private X509Certificate getX509CertfromBlob(final byte[] blob) {
		X509Certificate cert = null;
		try {
			final InputStream in = new ByteArrayInputStream(blob);
			final CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			cert = (X509Certificate) certFactory.generateCertificate(in);
		} catch (final CertificateException e) {
			throw new RuntimeException("Invalid message digest");
		}
		return cert;
	}

	private String generateAccessToken(final Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	private void populateDefaultValues(final Claims claims) {
		final Date issueAt = new Date();
		final Long expiration = issueAt.getTime() + (Long.parseLong("360") * 60000);
		claims.put(JWTClaimType.EXPIRATION.name(), expiration);
		claims.put(JWTClaimType.ISSUED_AT.name(), issueAt.getTime());
		claims.put(JWTClaimType.ID.name(), UUID.randomUUID());
		claims.put(JWTClaimType.ISSUER.name(), "https://capi.employeeservice.com");
		claims.put(JWTClaimType.JWT_TYPE.name(), JWTClaimType.JWT_VALUE.name());
		claims.put(JWTClaimType.AUDIENCE.name(), "https://capi.employeeservice.com/authorize");
	}

}
