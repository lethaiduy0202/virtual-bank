package com.virtualbank.mapper;

import com.virtualbank.dto.InvoicesTypeDto;
import com.virtualbank.entity.InvoicesType;

public class InvoicesTypeMapper {
  private InvoicesTypeMapper() {}

  public static InvoicesType convertToEntity(InvoicesTypeDto invoicesTypeDto) {
    return InvoicesType.builder().invoicesType(invoicesTypeDto.getInvoicesType()).build();
  }

}
