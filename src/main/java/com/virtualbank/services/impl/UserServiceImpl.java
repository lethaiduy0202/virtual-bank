package com.virtualbank.services.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.virtualbank.dto.UserDto;
import com.virtualbank.entity.User;
import com.virtualbank.exceptions.ResourceNotFoundException;
import com.virtualbank.mapper.UserMapper;
import com.virtualbank.repositories.UserRepository;
import com.virtualbank.security.VirtualBankUserDetails;
import com.virtualbank.services.UserService;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  

  @Override
  public UserDto saveUser(UserDto userDto) {
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    User userEntity = UserMapper.convertToEntity(userDto);
    userEntity = userRepository.save(userEntity);
    return UserMapper.convertToDTO(userEntity);
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

  @Override
  public Long getCurrentUserId() {
    VirtualBankUserDetails virtualUserDetails = (VirtualBankUserDetails) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();
    return virtualUserDetails.getId();
  }

  @Override
  public User getUserByUserId(Long userId) {
   Optional<User> userOpt =  userRepository.findById(userId);
   if (userOpt.isPresent()) {
     return userOpt.get();
   }
   else {
     throw new ResourceNotFoundException("user", String.valueOf(userId));
   }
  }

}
