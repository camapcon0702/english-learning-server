package com.nttmk.englishlearningserver.controllers;

import com.nttmk.englishlearningserver.dto.LoginDTO;
import com.nttmk.englishlearningserver.responses.ApiResponse;
import com.nttmk.englishlearningserver.responses.UserResponse;
import com.nttmk.englishlearningserver.services.UserService;
import com.nttmk.englishlearningserver.servicesInterface.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    // User controller

    private final IUserService userService;

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout() {
        userService.logout();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Logout success", "Logout Successful"));
    }


    @GetMapping("/current-user")
    public ResponseEntity<ApiResponse<UserResponse>> getCurrentUserLoggedIn() {
        UserResponse userResponse = userService.getCurrentUserLoggedIn();
        return ResponseEntity.status(HttpStatus.OK)
        .body(
            new ApiResponse<>(HttpStatus.OK.value(), 
                "Get current user logged in success", 
                        userResponse));
    }
}
