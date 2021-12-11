package com.virtualbank.services;

import com.virtualbank.dto.UserDto;
import com.virtualbank.entity.User;

public interface UserService {
  UserDto saveUser(UserDto userDto);
  
  User getCurrentUser();
  
  Long getCurrentUserId();
  
  User  getUserByUserId(Long userId);
}
