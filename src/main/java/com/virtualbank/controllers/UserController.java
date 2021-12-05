package com.virtualbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtualbank.dto.UserDTO;
import com.virtualbank.entity.User;
import com.virtualbank.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDTO> saveUser(@RequestBody(required = true) UserDTO userReq) {
    return ResponseEntity.ok(userService.saveUser(userReq));
  }

  @GetMapping("/current")
  public ResponseEntity<User> getCurrentUser() {
    return ResponseEntity.ok(userService.getCurrentUser());
  }
}
