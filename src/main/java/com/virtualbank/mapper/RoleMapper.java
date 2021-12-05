 package com.virtualbank.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.virtualbank.dto.RoleDTO;
import com.virtualbank.entity.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class RoleMapper {
  
  @Autowired
  private ModelMapper modelMapper;

  public Role convertToEntity(RoleDTO roleDTO) {
    Role role = new Role();
    role.setRoleName(roleDTO.getRoleName());
    return role;
  }
  
  public RoleDTO convertToDTO(Role role) {
    return modelMapper.map(role, RoleDTO.class);
  }
}
