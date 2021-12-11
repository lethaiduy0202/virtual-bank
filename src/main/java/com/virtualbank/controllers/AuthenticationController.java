package com.virtualbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtualbank.domain.AuthenResponse;
import com.virtualbank.dto.LoginDto;
import com.virtualbank.services.AuthenticationService;

@RestController
@RequestMapping("/authen")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authService;

  @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthenResponse> login(@RequestBody LoginDto loginReq) {
    return ResponseEntity.ok(authService.authenticate(loginReq));
  }

  @GetMapping(value = "/logout")
  public void logout() {}
  
  

}
