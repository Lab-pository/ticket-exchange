package com.ticketexchange.controller.dto;

import java.util.Base64;

import com.ticketexchange.service.dto.TokenDto;
import com.ticketexchange.utils.JsonUtils;

public class LoginResponse {
	private String token;

	protected LoginResponse() {
	}

	public LoginResponse(String token) {
		this.token = token;
	}

	public static LoginResponse of(TokenDto tokenDto) {
		String token = JsonUtils.toJson(tokenDto);
		return new LoginResponse(Base64.getEncoder().encodeToString(token.getBytes()));
	}

	public String getToken() {
		return token;
	}
}
