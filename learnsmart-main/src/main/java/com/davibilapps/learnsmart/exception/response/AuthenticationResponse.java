package com.davibilapps.learnsmart.exception.response;

import java.util.List;
public class AuthenticationResponse {
    private Long id;
    private String fullName;
    private String username;
    private Long healthCenterId;
    private List<String> roles;
    private String token;
    private String type = "Bearer";


    public AuthenticationResponse(String accessToken, Long id, String fullName, String username, Long healthCenterId, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.healthCenterId = healthCenterId;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Long getHealthCenterId() {
        return healthCenterId;
    }

    public void setHealthCenterId(Long healthCenterId) {
        this.healthCenterId = healthCenterId;
    }
}
