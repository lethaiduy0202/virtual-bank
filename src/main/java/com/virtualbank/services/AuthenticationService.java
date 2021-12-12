package com.virtualbank.services;

import com.virtualbank.domain.AuthenResponse;
import com.virtualbank.dto.LoginDto;
import com.virtualbank.dto.ResetPasswordDto;

public interface AuthenticationService {
  
  AuthenResponse authenticate(LoginDto loginDTO);
  
  void logout();
  
  void resetPassword(ResetPasswordDto resetPasswordDto);

}
