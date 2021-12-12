package com.virtualbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.virtualbank.domain.AuthenResponse;
import com.virtualbank.dto.LoginDto;
import com.virtualbank.dto.ResetPasswordDto;
import com.virtualbank.entity.User;
import com.virtualbank.enums.ErrorsEnum;
import com.virtualbank.exceptions.UnauthorizedException;
import com.virtualbank.repositories.UserRepository;
import com.virtualbank.security.JwtTokenProvider;
import com.virtualbank.security.VirtualBankUserDetails;
import com.virtualbank.services.AuthenticationService;
import com.virtualbank.services.UserService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Override
  public AuthenResponse authenticate(LoginDto loginDTO) {
    log.info("Starting login by user {}", loginDTO.getUsername());
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
    if (authentication != null) {
      VirtualBankUserDetails userDetail = (VirtualBankUserDetails) authentication.getPrincipal();
      String token = tokenProvider.createToken(userDetail.getId(), userDetail.getUsername());
      log.info("Finish login by user {}", loginDTO.getUsername());
      return new AuthenResponse(token, token);
    } else {
      throw new UnauthorizedException(ErrorsEnum.AUTHEN_FAIL.getErrorMessage());
    }
  }

  @Override
  public void logout() {
    log.info("Starting logout");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      VirtualBankUserDetails userDetail = (VirtualBankUserDetails) auth.getPrincipal();
      tokenProvider.revokeToken(userDetail.getId());
      SecurityContext context = SecurityContextHolder.getContext();
      context.setAuthentication(null);
      SecurityContextHolder.clearContext();
    }
    log.info("Finish logout");
  }

  @Override
  public void resetPassword(ResetPasswordDto resetPasswordDto) {
    log.info("Starting reset password");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      VirtualBankUserDetails userDetail = (VirtualBankUserDetails) auth.getPrincipal();
      User user = userService.getUserByUserId(userDetail.getId());
      user.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
      userRepository.save(user);
      tokenProvider.revokeToken(user.getId());
    }
    log.info("Finish reset password");
  }

}
