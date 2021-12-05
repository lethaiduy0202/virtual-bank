package com.virtualbank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
  private String firsrName;
  private String lastName;
  private String userName;
  private String password;
  private RoleDTO role;
}
