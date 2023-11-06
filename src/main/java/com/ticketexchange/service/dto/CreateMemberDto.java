package com.ticketexchange.service.dto;

import com.ticketexchange.domain.Member;

public class CreateMemberDto {

    private String email;
    private String nickname;
    private String password;

    protected CreateMemberDto() {
    }

    public CreateMemberDto(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public Member toEntity() {
        return new Member(email, nickname, password);
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
}
