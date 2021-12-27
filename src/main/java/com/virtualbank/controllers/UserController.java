package com.virtualbank.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtualbank.domain.UserResponse;
import com.virtualbank.dto.ProviderDto;
import com.virtualbank.dto.UserDto;
import com.virtualbank.enums.Providers;
import com.virtualbank.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public void saveUser(@RequestBody(required = true) UserDto userReq) {
    userService.saveUser(userReq);
  }
  
  @PostMapping(value = "/create/provider", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public void createProvider(@RequestBody(required = true) ProviderDto providerDto) {
    userService.createProvider(providerDto);
  }
  
  @GetMapping("/providers")
  public ResponseEntity<List<UserResponse>> getProviders() {
    return ResponseEntity.ok(userService.getProviders());
  }

  @GetMapping("/current")
  public ResponseEntity<UserResponse> getCurrentUser() {
    return ResponseEntity.ok(userService.getCurrentUser());
  }
}
