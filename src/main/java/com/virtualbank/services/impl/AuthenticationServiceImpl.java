package com.virtualbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.virtualbank.domain.AuthenResponse;
import com.virtualbank.dto.LoginDTO;
import com.virtualbank.security.JwtTokenProvider;
import com.virtualbank.security.VirtualBankUserDetails;
import com.virtualbank.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  private JwtTokenProvider tokenProvider;
  
  @Override
  public AuthenResponse authenticate(LoginDTO loginDTO) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword()));
    VirtualBankUserDetails userDetail = (VirtualBankUserDetails) authentication.getPrincipal();
    String token = tokenProvider.createToken(userDetail.getId(), userDetail.getUsername());
    return new AuthenResponse(token, token);

  }

}
