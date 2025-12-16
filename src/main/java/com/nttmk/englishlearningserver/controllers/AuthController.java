package com.nttmk.englishlearningserver.controllers;

import com.nttmk.englishlearningserver.dto.LoginDTO;
import com.nttmk.englishlearningserver.dto.RegisterDTO;
import com.nttmk.englishlearningserver.responses.ApiResponse;
import com.nttmk.englishlearningserver.responses.LoginResponse;
import com.nttmk.englishlearningserver.responses.UserResponse;
import com.nttmk.englishlearningserver.services.AuthService;
import com.nttmk.englishlearningserver.servicesInterface.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = authService.login(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Login Success", loginResponse));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody RegisterDTO registerDTO) {
        UserResponse userResponse = authService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(), "Register Success", userResponse));
    }
}
