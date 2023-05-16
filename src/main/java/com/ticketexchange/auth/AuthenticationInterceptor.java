package com.ticketexchange.auth;

import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ticketexchange.utils.JsonUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
	private static final String TOKEN_HEADER = "X-AUTH-TOKEN";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String encodedToken = request.getHeader(TOKEN_HEADER);

		if (encodedToken == null || encodedToken.equals("")) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}

		byte[] decode;

		try {
			decode = Base64.getDecoder().decode(encodedToken.getBytes());
		} catch(IllegalArgumentException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}

		MemberToken token = JsonUtils.fromJson(new String(decode), MemberToken.class);
		if (token.isExpired()) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}
		request.setAttribute("memberToken", token);
		return true;
	}
}
