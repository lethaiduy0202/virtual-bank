package com.virtualbank.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.virtualbank.domain.AccountResponse;
import com.virtualbank.domain.UserResponse;
import com.virtualbank.dto.ProviderDto;
import com.virtualbank.dto.UserDto;
import com.virtualbank.entity.Account;
import com.virtualbank.entity.User;
import com.virtualbank.enums.ErrorsEnum;
import com.virtualbank.enums.Providers;
import com.virtualbank.exceptions.AccountException;
import com.virtualbank.exceptions.ResourceNotFoundException;
import com.virtualbank.mapper.AccountMapper;
import com.virtualbank.mapper.UserMapper;
import com.virtualbank.repositories.AccountRepository;
import com.virtualbank.repositories.UserRepository;
import com.virtualbank.security.VirtualBankUserDetails;
import com.virtualbank.services.UserService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AccountRepository accountRepository;


  @Override
  public void saveUser(UserDto userDto) {
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    User userEntity = UserMapper.convertToEntity(userDto);
    userRepository.save(userEntity);
  }

  @Override
  public UserResponse getCurrentUser() {
    AccountResponse accountResponse = null;
    VirtualBankUserDetails virtualUserDetails = (VirtualBankUserDetails) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();
    Optional<User> userOpt = userRepository.findByUserName(virtualUserDetails.getUsername());
    if (userOpt.isPresent()) {
      Optional<Account> accountOpt = accountRepository.findByUser(userOpt.get());
      if (accountOpt.isPresent()) {
        accountResponse = AccountResponse.builder().accNumber(accountOpt.get().getAccNumber())
            .accBalance(accountOpt.get().getAccBalance()).build();
      }
      return UserResponse.builder().fullName(userOpt.get().getFullName()).id(userOpt.get().getId())
          .dateCreate(userOpt.get().getDateCreate()).username(userOpt.get().getUserName())
          .accountResponse(accountResponse).build();
    } else {
      throw new ResourceNotFoundException("user", "username");
    }
  }

  @Override
  public Long getCurrentUserId() {
    VirtualBankUserDetails virtualUserDetails = (VirtualBankUserDetails) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();
    return virtualUserDetails.getId();
  }

  @Override
  public User getUserByUserId(Long userId) {
    Optional<User> userOpt = userRepository.findById(userId);
    if (userOpt.isPresent()) {
      return userOpt.get();
    } else {
      throw new ResourceNotFoundException("user", String.valueOf(userId));
    }
  }

  @Override
  public void createProvider(ProviderDto providerDto) {
    try {
      providerDto.setPassword(passwordEncoder.encode(providerDto.getPassword()));
      User userEntity = UserMapper.convertToEntity(providerDto);
      User user = userRepository.save(userEntity);
      Optional<Account> accountOpt = accountRepository.findByUser(user);
      if (accountOpt.isPresent()) {
        throw new AccountException(ErrorsEnum.ACCOUNT_EXIST.getErrorMessage());
      }
      Account account = AccountMapper.convertToEntity(providerDto.getAccount(), user);
      account = accountRepository.save(account);
    } catch (Exception e) {
      log.info(e.getMessage());     
    }
  }

  @Override
  public List<UserResponse> getProviders() {
    List<UserResponse> userResponses = new ArrayList<>();
    Arrays.asList(Providers.values()).stream().forEach(provider -> {
      Optional<User> userOpt = userRepository.findByUserName(provider.name());
      if (userOpt.isPresent()) {
        Optional<Account> accountOpt = accountRepository.findByUser(userOpt.get());
        if (accountOpt.isPresent()) {
          AccountResponse accountResponse = AccountResponse.builder().accNumber(accountOpt.get().getAccNumber())
              .accBalance(accountOpt.get().getAccBalance()).build();
          UserResponse userResponse = UserResponse.builder().fullName(userOpt.get().getFullName()).id(userOpt.get().getId())
              .dateCreate(userOpt.get().getDateCreate()).username(userOpt.get().getUserName())
              .accountResponse(accountResponse).build();
          userResponses.add(userResponse);
        }
      } else {
        throw new ResourceNotFoundException("user", "username");
      }
    });
    return userResponses;
  }

}
