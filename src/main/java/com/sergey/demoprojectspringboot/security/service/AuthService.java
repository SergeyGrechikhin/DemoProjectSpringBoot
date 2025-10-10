package com.sergey.demoprojectspringboot.security.service;

import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.security.dto.AuthMeResponse;
import com.sergey.demoprojectspringboot.security.dto.AuthRequest;
import com.sergey.demoprojectspringboot.security.entity.MyEmployeeToEmployeeDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService customUserDetailService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Generate JWT token for a user with username
     * @param request
     * @return String variable with JWT token
     */
    public String generateJwt(AuthRequest request) {

        try {
            customUserDetailService.loadUserByUsername(request.getUsername());
        } catch (UsernameNotFoundException e){
            throw new NotFoundException("User with email: " + request.getUsername() + " is not registered");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.createToken(request.getUsername());
    }

    public AuthMeResponse getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !(authentication.getPrincipal() instanceof MyEmployeeToEmployeeDetails details)){
            throw new RuntimeException("User not authenticated");
        }

        Employee employee = details.getEmployee();
        return new AuthMeResponse(
                employee.getEmail(),
                employee.getRole().name(),
                employee.getStatus().name()
        );
    }

}
