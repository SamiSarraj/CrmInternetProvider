package com.crm.security;

public class AuthorizationToken {

    private String token;

    public AuthorizationToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}