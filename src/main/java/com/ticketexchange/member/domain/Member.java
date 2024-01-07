package com.ticketexchange.member.domain;

public class Member {

    private final Long id;
    private final String email;
    private final String nickname;
    private final String password;

    public Member(final Long id, final String email, final String nickname, final String password) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public boolean isSamePassword(final String password) {
        return this.password.equals(password);
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getPassword() {
        return this.password;
    }
}
