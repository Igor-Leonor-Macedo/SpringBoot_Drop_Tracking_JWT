package com.SpringBoot.Drop_Tracking_JWT.repository;

import com.SpringBoot.Drop_Tracking_JWT.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert,Long> {
}
