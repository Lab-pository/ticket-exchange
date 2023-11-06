package com.ticketexchange.controller.dto;

import com.ticketexchange.service.dto.CreateMemberDto;

public class MemberRequest {

    private String email;
    private String nickname;
    private String password;

    protected MemberRequest() {
    }

    public MemberRequest(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public CreateMemberDto toCreateMemberDto() {
        return new CreateMemberDto(email, nickname, password);
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
