package com.ticketlounge.web.member.response;

import java.util.Base64;

import com.ticketlounge.domain.member.Token;
import com.ticketlounge.web.utils.JsonUtils;

public record LoginResponse(String token) {

    public static LoginResponse from(final Token token) {
        return new LoginResponse(Base64.getEncoder().encodeToString(JsonUtils.toJson(token).getBytes()));
    }
}
