package com.virtualbank.domain;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoicesRepsonse {
  private Long userId;
  private Month month;
  private List<InvoicesDetailResponse> invoiceDetails;
  private BigDecimal totalConsumption;

}
