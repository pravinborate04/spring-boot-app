package com.borate.pravin.pim.dto;

/**
 * Dto for Authentication Request
 *
 * @author Pravin Borate
 * 14/03/21
 */
public class AuthenticationRequest {

    private String username;

    private String password;

    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
