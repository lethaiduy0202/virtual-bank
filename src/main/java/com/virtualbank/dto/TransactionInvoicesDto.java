package com.virtualbank.dto;

import java.time.Month;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionInvoicesDto {
  private Month month;
  private List<InfoTranferDto> infoTranferDtos;
}
