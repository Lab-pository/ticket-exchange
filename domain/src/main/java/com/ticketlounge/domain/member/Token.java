package com.ticketlounge.domain.member;

import java.time.Instant;

public class Token {

    private static final Long TOKEN_EXPIRE = 1000L * 60 * 60 * 24 * 7;

    private final Long id;
    private final Long expire;

    public Token(final Long id) {
        this.id = id;
        this.expire = Instant.now().toEpochMilli() + TOKEN_EXPIRE;
    }

    public Long getId() {
        return this.id;
    }

    public Long getExpire() {
        return this.expire;
    }

}
