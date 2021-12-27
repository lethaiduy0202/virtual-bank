package com.virtualbank.mapper;

import java.util.Date;
import com.virtualbank.dto.ProviderDto;
import com.virtualbank.dto.UserDto;
import com.virtualbank.entity.User;


public class UserMapper {
  private UserMapper() {}

  public static User convertToEntity(UserDto userDto) {
    return User.builder().fullName(userDto.getFullName()).dateCreate(new Date())
        .userName(userDto.getUserName()).password(userDto.getPassword()).build();

  }
  
  public static User convertToEntity(ProviderDto providerDto) {
    return User.builder().fullName(providerDto.getProvider().getDetail()).dateCreate(new Date())
        .userName(providerDto.getProvider().name()).password(providerDto.getPassword()).build();

  }

  public static UserDto convertToDTO(User user) {
    return UserDto.builder().fullName(user.getFullName())
        .userName(user.getUserName()).build();
  }
}
