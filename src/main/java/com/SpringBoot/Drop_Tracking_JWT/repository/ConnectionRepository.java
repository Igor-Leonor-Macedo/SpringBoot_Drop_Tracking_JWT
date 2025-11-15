package com.SpringBoot.Drop_Tracking_JWT.repository;

import com.SpringBoot.Drop_Tracking_JWT.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConnectionRepository  extends JpaRepository<Connection, Long> {
    Optional<Connection> findTopByDeviceIdAndLastSeenIsNullOrderByFirstSeenDesc(Long id);
}
