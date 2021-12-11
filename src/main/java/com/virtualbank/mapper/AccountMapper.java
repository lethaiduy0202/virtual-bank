package com.virtualbank.mapper;

import com.virtualbank.dto.AccountDto;
import com.virtualbank.entity.Account;
import com.virtualbank.entity.User;


public class AccountMapper {
  private AccountMapper() {}

  public static Account convertToEntity(AccountDto accountDto, User user) {
    return Account.builder().accNumber(accountDto.getAccNumber())
        .accBalance(accountDto.getAccBanlance()).user(user).build();

  }

  public static AccountDto convertToDTO(Account account) {
    return AccountDto.builder().accBanlance(account.getAccBalance())
        .accNumber(account.getAccNumber()).build();

  }
}
