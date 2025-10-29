package com.SpringBoot.Drop_Tracking_JWT;

import com.SpringBoot.Drop_Tracking_JWT.entity.User;
import com.SpringBoot.Drop_Tracking_JWT.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StartApplication implements CommandLineRunner {

    private final UserRepository repository;
    public StartApplication(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        // Criação do usuário admin
        createUserIfNotExists("698.596.291-29", "123", "ROLE_ADMIN");

        // Criação do usuário user
        createUserIfNotExists("538.459.824-70", "123", "ROLE_USER");

        // Criação do usuário user
        createUserIfNotExists("750.802.731-00", "123", "ROLE_MANAGER");
    }

    private void createUserIfNotExists(String cpf, String password, String role) {
        Optional<User> optionalUser = repository.findByCpf(cpf);

        if (optionalUser.isEmpty()) {
            User user = new User();
            user.setCpf(cpf);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.getRoles().add(role);
            repository.save(user);
        }
    }
}
