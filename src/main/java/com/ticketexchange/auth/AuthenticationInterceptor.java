package com.ticketexchange.auth;

import java.util.Base64;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ticketexchange.utils.JsonUtils;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final String TOKEN_HEADER = "X-AUTH-TOKEN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String encodedToken = request.getHeader(TOKEN_HEADER);
        if (encodedToken == null || encodedToken.isBlank()) {
            return true;
        }
        byte[] decode = Base64.getDecoder().decode(encodedToken.getBytes());
        MemberToken token = JsonUtils.fromJson(new String(decode), MemberToken.class);
        if (token.isExpired()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        request.setAttribute("memberToken", token);
        return true;
    }
}
