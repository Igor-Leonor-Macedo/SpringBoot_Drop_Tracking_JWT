package com.SpringBoot.Drop_Tracking_JWT.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cpf;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="USER_ROLES", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "role_id")
    private List<String> roles = new ArrayList<>();

    public User(String cpf, String password, List<String> roles) {
        this.cpf = cpf;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
