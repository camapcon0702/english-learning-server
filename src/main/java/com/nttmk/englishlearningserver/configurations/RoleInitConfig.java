package com.nttmk.englishlearningserver.configurations;

import com.nttmk.englishlearningserver.models.Role;
import com.nttmk.englishlearningserver.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class RoleInitConfig {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        createIfNotExists("ADMIN");
        createIfNotExists("USER");
    }

    private void createIfNotExists(String roleName) {
        roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(
                        Role.builder()
                                .name(roleName)
                                .build()
                ));
    }
}
