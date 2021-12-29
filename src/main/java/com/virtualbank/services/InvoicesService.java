package com.virtualbank.services;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import com.virtualbank.domain.InvoicesRepsonse;
import com.virtualbank.dto.InvoicesDto;
import com.virtualbank.dto.InvoicesTypeDto;
import com.virtualbank.entity.Invoices;
import com.virtualbank.exceptions.InvoicesException;

public interface InvoicesService {
  void saveInvoicesType(InvoicesTypeDto invoicesDto);

  void saveInvoices(Long userId, InvoicesDto invoicesDto) throws InvoicesException;

  InvoicesRepsonse getInvoices(Long userId, Month month);

  List<Invoices> getInvoicesEntity(Long userId, Month month);

  void updateAmountInvoices(Long userId, Invoices invoiceReq, BigDecimal amountMoneyUpdation);

}
