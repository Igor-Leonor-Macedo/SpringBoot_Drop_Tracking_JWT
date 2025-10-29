package com.SpringBoot.Drop_Tracking_JWT.repository;

import com.SpringBoot.Drop_Tracking_JWT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
