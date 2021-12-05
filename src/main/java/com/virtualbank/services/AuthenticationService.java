package com.virtualbank.services;

import com.virtualbank.domain.AuthenResponse;
import com.virtualbank.dto.LoginDTO;

public interface AuthenticationService {
  
  AuthenResponse authenticate(LoginDTO loginDTO);

}
