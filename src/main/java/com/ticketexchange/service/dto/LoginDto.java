package com.ticketexchange.service.dto;

public class LoginDto {

    private String email;
    private String password;

    protected LoginDto() {
    }

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
