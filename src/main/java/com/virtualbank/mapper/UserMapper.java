package com.virtualbank.mapper;

import java.util.Date;
import com.virtualbank.dto.RoleDTO;
import com.virtualbank.dto.UserDTO;
import com.virtualbank.entity.Role;
import com.virtualbank.entity.User;


public class UserMapper {

  public User convertToEntity(UserDTO userDto, Role role) {
    return User.builder().firstName(userDto.getFirsrName()).lastName(userDto.getLastName())
        .dateCreate(new Date()).userName(userDto.getUserName()).password(userDto.getPassword())
        .role(role).build();

  }

  public UserDTO convertToDTO(User user) {
    RoleDTO role = new RoleDTO(user.getRole().getRoleName());
    return UserDTO.builder().firsrName(user.getFirstName()).lastName(user.getLastName())
        .password(user.getPassword()).userName(user.getUserName()).role(role).build();
  }
}
