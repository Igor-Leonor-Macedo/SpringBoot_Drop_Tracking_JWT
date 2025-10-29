package com.SpringBoot.Drop_Tracking_JWT.dto.request;

import java.util.ArrayList;
import java.util.List;

public class UserRequestDto {


    private String cpf;
    private String password;
    private List<String> roles = new ArrayList<>();

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}