package com.sergey.demoprojectspringboot.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/user")
public interface UserProfileApi {

   @PutMapping("/deactivate")
   ResponseEntity<Void> deactivateMe();

   @PutMapping("/reactivateMe")
    ResponseEntity<Void> reactivateMe();


}
