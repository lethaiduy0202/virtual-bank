package com.virtualbank.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InfoTranferDto {
  private String accounReceiver;
  private String username;
  private BigDecimal amount;
  private String content;
}
