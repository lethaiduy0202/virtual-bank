package com.virtualbank.dto;

import com.virtualbank.enums.Providers;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProviderDto {
 private Providers provider;
 private String password;
 private AccountDto account;
}
