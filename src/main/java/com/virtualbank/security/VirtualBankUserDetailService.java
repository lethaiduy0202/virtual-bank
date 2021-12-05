package com.virtualbank.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.virtualbank.entity.User;
import com.virtualbank.exceptions.ResourceNotFoundException;
import com.virtualbank.repositories.UserRepository;

@Service
public class VirtualBankUserDetailService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userOpt = userRepository.findByUserName(username);
    if (userOpt.isPresent()) {
      return new VirtualBankUserDetails(userOpt.get());
    } else {
      throw new ResourceNotFoundException("User", username);
    }

  }

}
