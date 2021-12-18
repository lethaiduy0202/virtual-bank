package com.virtualbank.domain;

import java.math.BigDecimal;
import com.virtualbank.entity.InvoicesType;
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
public class InvoicesDetailResponse {
 private Long id;
 private Long consumption;
 private BigDecimal billAmount;
 private InvoicesType invoicesType;
}
