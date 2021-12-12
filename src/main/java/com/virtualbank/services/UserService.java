package com.virtualbank.services;

import com.virtualbank.domain.UserResponse;
import com.virtualbank.dto.UserDto;
import com.virtualbank.entity.User;

public interface UserService {
  void saveUser(UserDto userDto);
  
  UserResponse getCurrentUser();
  
  Long getCurrentUserId();
  
  User  getUserByUserId(Long userId);
}
