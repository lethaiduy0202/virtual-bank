package com.virtualbank.services;

import com.virtualbank.dto.UserDTO;
import com.virtualbank.entity.User;

public interface UserService {
  UserDTO saveUser(UserDTO userDto);
  
  User getCurrentUser();
}
