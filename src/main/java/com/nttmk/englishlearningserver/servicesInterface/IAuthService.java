package com.nttmk.englishlearningserver.servicesInterface;

import com.nttmk.englishlearningserver.dto.LoginDTO;
import com.nttmk.englishlearningserver.dto.RegisterDTO;
import com.nttmk.englishlearningserver.responses.LoginResponse;
import com.nttmk.englishlearningserver.responses.UserResponse;

public interface IAuthService {
    public LoginResponse login(LoginDTO loginDTO);
    public UserResponse register(RegisterDTO registerDTO);
}
