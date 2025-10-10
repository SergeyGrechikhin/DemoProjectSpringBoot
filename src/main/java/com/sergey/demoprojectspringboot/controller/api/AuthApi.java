package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.security.dto.AuthMeResponse;
import com.sergey.demoprojectspringboot.security.dto.AuthRequest;
import com.sergey.demoprojectspringboot.security.dto.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
public interface AuthApi {
    @PostMapping
    ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request);

    @GetMapping("/me")
    ResponseEntity<AuthMeResponse> getAuth();

}
