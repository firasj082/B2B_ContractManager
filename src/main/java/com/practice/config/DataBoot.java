package com.practice.config;

import com.practice.models.Role;
import com.practice.models.User;
import com.practice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataBoot implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRole(Role.ROLE_ADMIN);
            admin.setEnabled(true);

            userRepository.save(admin);
        }
        if (userRepository.findByUsername("user").isEmpty()) {

            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRole(Role.ROLE_USER);
            user.setEnabled(true);

            userRepository.save(user);
        }
    }
}
