package com.virtualbank.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.virtualbank.entity.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class TransactionHistoryResponse {
  private Long id;
  private String accountNumber;
  private String fullName;
  private TransactionType transType;
  private String transNote;
  private Date transDate;
  private BigDecimal transAmount;
}
