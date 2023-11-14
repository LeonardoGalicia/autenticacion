package com.boster.proyectoAPI.models;

public class TokenInfo {
    private final String jwtToken;

    public TokenInfo(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }
}
