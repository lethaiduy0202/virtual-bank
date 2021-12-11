package com.virtualbank.services;

import com.virtualbank.domain.AuthenResponse;
import com.virtualbank.dto.LoginDto;

public interface AuthenticationService {
  
  AuthenResponse authenticate(LoginDto loginDTO);

}
