package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.controller.api.AuthApi;
import com.sergey.demoprojectspringboot.security.dto.AuthRequest;
import com.sergey.demoprojectspringboot.security.dto.AuthResponse;
import com.sergey.demoprojectspringboot.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<AuthResponse> authenticate(AuthRequest request) {

        String jwt = authService.generateJwt(request);

        return new ResponseEntity<>(new AuthResponse(jwt), HttpStatus.OK);
    }
}
