package com.SpringBoot.Drop_Tracking_JWT.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="ALERT")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "infusion_id")
    private Infusion infusion;

    private String typeAlert;
    private LocalDateTime alertDateTime;

    public Alert() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Infusion getInfusion() {
        return infusion;
    }

    public void setInfusion(Infusion infusion) {
        this.infusion = infusion;
    }

    public String getTypeAlert() {
        return typeAlert;
    }

    public void setTypeAlert(String typeAlert) {
        this.typeAlert = typeAlert;
    }

    public LocalDateTime getAlertDateTime() {
        return alertDateTime;
    }

    public void setAlertDateTime(LocalDateTime alertDateTime) {
        this.alertDateTime = alertDateTime;
    }
}
