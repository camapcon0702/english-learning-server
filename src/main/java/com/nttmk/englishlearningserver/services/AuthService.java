package com.nttmk.englishlearningserver.services;

import com.nttmk.englishlearningserver.dto.LoginDTO;
import com.nttmk.englishlearningserver.dto.RegisterDTO;
import com.nttmk.englishlearningserver.jwt.JwtProvider;
import com.nttmk.englishlearningserver.models.Role;
import com.nttmk.englishlearningserver.models.User;
import com.nttmk.englishlearningserver.repository.RoleRepository;
import com.nttmk.englishlearningserver.repository.UserRepository;
import com.nttmk.englishlearningserver.responses.LoginResponse;
import com.nttmk.englishlearningserver.responses.UserResponse;
import com.nttmk.englishlearningserver.servicesInterface.IAuthService;
import com.nttmk.englishlearningserver.servicesInterface.IUserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final IUserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserResponse userResponse = new UserResponse(user.getId(), user.getFullName(), user.getEmail(), user.getRole(), user.getCreatedAt());

        return LoginResponse.builder()
                .user(userResponse)
                .token(token)
                .build();
    }

    @Override
    public UserResponse register(RegisterDTO registerDTO) {
        if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new UsernameNotFoundException("Email already exists");
        }

        Role role = roleRepository
                .findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found"));


        User user = User.builder()
                .fullName(registerDTO.getFullName())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(role.getName())
                .roleId(role.getId())
                .createdAt(Instant.now())
                .build();

        userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .build();
    }
}
