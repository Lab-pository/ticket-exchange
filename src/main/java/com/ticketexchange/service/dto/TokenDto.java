package com.ticketexchange.service.dto;

public class TokenDto {

    private Long id;
    private Long expire;

    protected TokenDto() {
    }

    public TokenDto(Long id, Long expire) {
        this.id = id;
        this.expire = expire;
    }

    public static TokenDto of(Long id, Long expire) {
        return new TokenDto(id, expire);
    }

    public Long getId() {
        return id;
    }

    public Long getExpire() {
        return expire;
    }
}
