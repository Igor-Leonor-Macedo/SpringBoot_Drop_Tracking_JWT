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
        // Criação do usuário user
        createUserIfNotExists("Joaquim", "538.459.824-70", "joaquim@gmail.com","123","ROLE_USER");
        // Criação do usuário admin
        createUserIfNotExists("João", "698.596.291-29", "joão@gmail.com","123","ROLE_ADMIN");
        // Criação do usuário manager
        createUserIfNotExists("Igor", "750.802.731-00", "iglmacedo@gmail.com","123","ROLE_MANAGER");
    }

    private void createUserIfNotExists(String name,String cpf, String email,String password, String role) {
        Optional<User> optionalUser = repository.findByCpf(cpf);

        if (optionalUser.isEmpty()) {
            User user = new User();
            user.setName(name);
            user.setCpf(cpf);
            user.setEmail(email);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.getRoles().add(role);
            repository.save(user);
        }
    }
}
