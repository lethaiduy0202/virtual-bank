package com.virtualbank.mapper;

import com.virtualbank.dto.InvoicesDto;
import com.virtualbank.entity.Invoices;
import com.virtualbank.entity.InvoicesType;
import com.virtualbank.entity.User;

public class InvoicesMapper {

  private InvoicesMapper() {}

  public static Invoices convertToEntity(InvoicesDto invoicesDto, User user,
      InvoicesType invoicesType) {
    return Invoices.builder().billMonth(invoicesDto.getBillMonth().name())
        .billAmount(invoicesDto.getBillAmount()).consumption(invoicesDto.getConsumption())
        .user(user).invoicesType(invoicesType).isPay(false).build();
  }

}
