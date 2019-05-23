package com.employee.service.validator;

import org.springframework.stereotype.Component;

import com.employee.service.beans.AccessToken;

import io.jsonwebtoken.JwtHandlerAdapter;
import io.jsonwebtoken.Jwts;

@Component
public class AccessTokenValidator extends JwtHandlerAdapter<AccessToken> {

	public AccessToken validateAccessToken(final String accessToken, final String secret) {
		return Jwts.parser().setSigningKey(secret).parse(accessToken, this);
	}

}