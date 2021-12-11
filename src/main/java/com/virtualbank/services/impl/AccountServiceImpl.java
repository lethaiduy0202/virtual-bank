package com.virtualbank.services.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.virtualbank.domain.AccountResponse;
import com.virtualbank.dto.AccountDto;
import com.virtualbank.entity.Account;
import com.virtualbank.entity.User;
import com.virtualbank.enums.ErrorsEnum;
import com.virtualbank.exceptions.AccountException;
import com.virtualbank.mapper.AccountMapper;
import com.virtualbank.mapper.UserMapper;
import com.virtualbank.repositories.AccountRepository;
import com.virtualbank.services.AccountService;
import com.virtualbank.services.UserService;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  private UserService userService;

  @Autowired
  private  AccountRepository accountRepository;

  @Override
  public AccountResponse createAccount(Long userId, AccountDto accountDto) throws AccountException {
    User user = userService.getUserByUserId(userId);
    Optional<Account> accountOpt =  accountRepository.findByUser(user);
    if (accountOpt.isPresent()) {
      throw new AccountException(ErrorsEnum.ACCOUNT_EXIST.getErrorMessage());
    }
    Account account = AccountMapper.convertToEntity(accountDto, user);
    account = accountRepository.save(account);
    return AccountResponse.builder().accNumber(account.getAccNumber())
        .accBalance(account.getAccBalance()).user(UserMapper.convertToDTO(user)).build();
  }

}
