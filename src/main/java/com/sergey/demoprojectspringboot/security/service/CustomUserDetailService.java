package com.sergey.demoprojectspringboot.security.service;

import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.security.entity.MyEmployeeToEmployeeDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final EmployeeRepositoryDataBase repository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return repository.findByEmail(userEmail)
                .map(user -> new MyEmployeeToEmployeeDetails(user))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + userEmail + " not found"));
    }
}

