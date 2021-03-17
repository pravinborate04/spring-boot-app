package com.borate.pravin.pim.dto;

/**
 * Dto for providing Authentication Response
 *
 * @author Pravin Borate
 * 14/03/21
 */
public class AuthenticationResponse {
    private final String token;

    private final String username;
    private final String role;

    public AuthenticationResponse(String token, String username, String role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }
}
