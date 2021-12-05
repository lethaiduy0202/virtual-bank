package com.virtualbank.services;

import com.virtualbank.dto.RoleDTO;
import com.virtualbank.entity.Role;
import com.virtualbank.exceptions.RoleException;

public interface RoleService {
  
  Role saveRole(RoleDTO roleDTO) throws RoleException;
  
  Role getRole(String roleName);

}
