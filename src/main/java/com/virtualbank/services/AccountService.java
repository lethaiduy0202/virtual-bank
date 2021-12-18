package com.virtualbank.services;

import com.virtualbank.domain.AccountResponse;
import com.virtualbank.domain.UserResponse;
import com.virtualbank.dto.AccountDto;
import com.virtualbank.exceptions.AccountException;

public interface AccountService {
  AccountResponse createAccount(Long userId, AccountDto accountDto) throws AccountException;
  
  UserResponse getUserByAccount(String accountNumber) throws AccountException;
}
