package com.virtualbank.domain;

import java.math.BigDecimal;
import com.virtualbank.dto.UserDto;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountResponse {
  private String accNumber;
  private BigDecimal accBalance;
}
