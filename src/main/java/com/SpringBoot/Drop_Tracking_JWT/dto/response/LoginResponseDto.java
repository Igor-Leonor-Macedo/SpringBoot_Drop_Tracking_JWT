package com.SpringBoot.Drop_Tracking_JWT.dto.response;

import java.util.List;

public class LoginResponseDto {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private List<String> roles;
    private Boolean authenticated;
    private String token;

    public LoginResponseDto(Long id, String name, String cpf, String email, List<String> roles, boolean authenticated, String token) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.roles = roles;
        this.authenticated = authenticated;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
