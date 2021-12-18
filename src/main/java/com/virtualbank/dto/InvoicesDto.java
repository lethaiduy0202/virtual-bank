package com.virtualbank.dto;

import java.math.BigDecimal;
import java.time.Month;
import com.virtualbank.enums.InvoicesTypesEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoicesDto {
  private Month billMonth;
  private Long consumption;
  private BigDecimal billAmount;
  private InvoicesTypesEnum invoicesTypes;

}
