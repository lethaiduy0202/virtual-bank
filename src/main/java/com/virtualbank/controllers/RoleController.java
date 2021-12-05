package com.virtualbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtualbank.dto.RoleDTO;
import com.virtualbank.entity.Role;
import com.virtualbank.services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Role> saveRole(RoleDTO roleDTO) throws Exception {
    return ResponseEntity.ok(roleService.saveRole(roleDTO));
  }
}
