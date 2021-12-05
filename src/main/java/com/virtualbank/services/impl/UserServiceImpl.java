package com.virtualbank.services.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.virtualbank.dto.UserDTO;
import com.virtualbank.entity.Role;
import com.virtualbank.entity.User;
import com.virtualbank.exceptions.ResourceNotFoundException;
import com.virtualbank.mapper.UserMapper;
import com.virtualbank.repositories.UserRepository;
import com.virtualbank.security.VirtualBankUserDetails;
import com.virtualbank.services.RoleService;
import com.virtualbank.services.UserService;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleService roleService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDTO saveUser(UserDTO userDto) {
    UserMapper mapper = new UserMapper();
    Role role = roleService.getRole(userDto.getRole().getRoleName());
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    User userEntity = mapper.convertToEntity(userDto, role);
    userEntity = userRepository.save(userEntity);
    return mapper.convertToDTO(userEntity);
  }

  @Override
  public User getCurrentUser() {
    VirtualBankUserDetails virtualUserDetails = (VirtualBankUserDetails) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();
    Optional<User> userOpt = userRepository.findByUserName(virtualUserDetails.getUsername());
    if (userOpt.isPresent()) {
      return userOpt.get();
    } else {
      throw new ResourceNotFoundException("user", "username");
    }
  }

}
