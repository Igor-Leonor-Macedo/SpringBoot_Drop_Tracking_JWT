package com.SpringBoot.Drop_Tracking_JWT.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PATIENT")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String medicalRecord;
    private String patientName;
    private String nameBedRoom;
    private LocalDateTime criationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id",nullable = false)
    private Device device;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public Patient(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getNameBedRoom() {
        return nameBedRoom;
    }

    public void setNameBedRoom(String nameBedRoom) {
        this.nameBedRoom = nameBedRoom;
    }

    public LocalDateTime getCriationDate() {
        return criationDate;
    }

    public void setCriationDate(LocalDateTime criationDate) {
        this.criationDate = criationDate;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
