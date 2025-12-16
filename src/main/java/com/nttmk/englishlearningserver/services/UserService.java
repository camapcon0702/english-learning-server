package com.nttmk.englishlearningserver.services;

import com.nttmk.englishlearningserver.dto.LoginDTO;
import com.nttmk.englishlearningserver.dto.RegisterDTO;
import com.nttmk.englishlearningserver.exceptions.DataNotFoundException;
import com.nttmk.englishlearningserver.models.User;
import com.nttmk.englishlearningserver.repository.UserRepository;
import com.nttmk.englishlearningserver.responses.UserResponse;
import com.nttmk.englishlearningserver.servicesInterface.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    @Override
    public UserResponse getCurrentUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("User not found"));
        return new UserResponse(user.getId(), user.getFullName(), user.getEmail(), user.getRole(), user.getCreatedAt());
    }
}
