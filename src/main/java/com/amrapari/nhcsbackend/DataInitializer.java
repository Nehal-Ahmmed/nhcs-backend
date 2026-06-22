package com.amrapari.nhcsbackend;

import com.amrapari.nhcsbackend.domain.Role;
import com.amrapari.nhcsbackend.domain.User;
import com.amrapari.nhcsbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createMockUserIfNotExists("patient", "patient@nhcs.gov", Role.PATIENT);
        createMockUserIfNotExists("doctor", "doctor@nhcs.gov", Role.DOCTOR);
        createMockUserIfNotExists("hospital", "hospital@nhcs.gov", Role.HOSPITAL);
        createMockUserIfNotExists("govt", "govt@nhcs.gov", Role.GOVT);
    }

    private void createMockUserIfNotExists(String username, String email, Role role) {
        if (userRepository.findByUsername(username).isEmpty()) {
            User user = User.builder()
                    .username(username)
                    .email(email)
                    .password(passwordEncoder.encode("password123"))
                    .role(role)
                    .build();
            userRepository.save(user);
        }
    }
}
