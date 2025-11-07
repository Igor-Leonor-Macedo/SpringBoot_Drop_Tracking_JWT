package com.SpringBoot.Drop_Tracking_JWT.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "INFUSION")
public class Infusion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="patient_id",nullable=false)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="device_id",nullable=false)
    private Device device;

    private LocalDateTime infusionDateTime;
    private int infusion;

    public Infusion(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public LocalDateTime getInfusionDateTime() {
        return infusionDateTime;
    }

    public void setInfusionDateTime(LocalDateTime infusionDateTime) {
        this.infusionDateTime = infusionDateTime;
    }

    public int getInfusion() {
        return infusion;
    }

    public void setInfusion(int infusion) {
        this.infusion = infusion;
    }
}
