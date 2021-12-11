package com.virtualbank.services;

import com.virtualbank.domain.AccountResponse;
import com.virtualbank.dto.AccountDto;
import com.virtualbank.exceptions.AccountException;

public interface AccountService {
  AccountResponse createAccount(Long userId, AccountDto accountDto) throws AccountException;
}
