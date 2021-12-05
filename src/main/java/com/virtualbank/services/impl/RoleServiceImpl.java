package com.virtualbank.services.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.virtualbank.dto.RoleDTO;
import com.virtualbank.entity.Role;
import com.virtualbank.enums.ErrorsEnum;
import com.virtualbank.exceptions.RoleException;
import com.virtualbank.exceptions.ResourceNotFoundException;
import com.virtualbank.mapper.RoleMapper;
import com.virtualbank.repositories.RoleRepository;
import com.virtualbank.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role saveRole(RoleDTO roleDTO) throws RoleException {
    Optional<Role> roleOpt = roleRepository.findByRoleName(roleDTO.getRoleName());
    if (roleOpt.isPresent()) {
      throw new RoleException(ErrorsEnum.ROLE_EXIST.getErrorMessage());
    }
    RoleMapper roleMapper = new RoleMapper();
    Role role = roleMapper.convertToEntity(roleDTO);
    return roleRepository.save(role);
  }

  @Override
  public Role getRole(String roleName) {
    Optional<Role> roleOpt = roleRepository.findByRoleName(roleName);
    if (roleOpt.isPresent()) {
      return roleOpt.get();
    } else {
      throw new ResourceNotFoundException("role", roleName);
    }
   
  }
 

}
