package com.nttmk.englishlearningserver.services;

import com.nttmk.englishlearningserver.dto.LoginDTO;
import com.nttmk.englishlearningserver.dto.RegisterDTO;
import com.nttmk.englishlearningserver.responses.UserResponse;
import com.nttmk.englishlearningserver.servicesInterface.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
