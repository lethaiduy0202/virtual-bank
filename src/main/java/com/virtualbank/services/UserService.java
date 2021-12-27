package com.virtualbank.services;

import java.util.List;
import com.virtualbank.domain.UserResponse;
import com.virtualbank.dto.ProviderDto;
import com.virtualbank.dto.UserDto;
import com.virtualbank.entity.User;

public interface UserService {
  void saveUser(UserDto userDto);
  
  void createProvider(ProviderDto providerDto);
  
  UserResponse getCurrentUser();
  
  List<UserResponse> getProviders();
  
  Long getCurrentUserId();
  
  User  getUserByUserId(Long userId);
}
