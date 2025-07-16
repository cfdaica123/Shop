package com.diorama.shop.dto.common.response;

public class AuthResponseDTO {
    private String token;
    private String username;

    // Constructors
    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String token, String username) {
        this.token = token;
        this.username = username;
    }

    // Getters & Setters
    /**
     * @return String return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

}