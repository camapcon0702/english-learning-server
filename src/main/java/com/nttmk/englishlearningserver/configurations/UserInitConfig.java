package com.nttmk.englishlearningserver.configurations;

import com.nttmk.englishlearningserver.models.Role;
import com.nttmk.englishlearningserver.models.User;
import com.nttmk.englishlearningserver.repository.RoleRepository;
import com.nttmk.englishlearningserver.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserInitConfig {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAdminUser() {

        if (userRepository.existsByEmail("admin@gmail.com")) {
            return;
        }

        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("ADMIN role not found"));

        User admin = User.builder()
                .fullName("System Admin")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin123"))
                .role(adminRole.getName())
                .roleId(adminRole.getId())
                .build();

        userRepository.save(admin);
    }
}
