package com.virtualbank.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResetPasswordDto {
  private String oldPassword;
  private String newPassword;
}
