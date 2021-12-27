package com.virtualbank.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountSavedResponse {
  private String accountNumber;
  private String fullName;
  private String username;
}
