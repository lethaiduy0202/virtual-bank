package com.virtualbank.services;

import java.time.Month;
import com.virtualbank.domain.InvoicesRepsonse;
import com.virtualbank.dto.InvoicesDto;
import com.virtualbank.dto.InvoicesTypeDto;
import com.virtualbank.exceptions.InvoicesException;

public interface InvoicesService {
  void saveInvoicesType(InvoicesTypeDto invoicesDto);

  void saveInvoices(Long userId, InvoicesDto invoicesDto) throws InvoicesException;
  
  InvoicesRepsonse getInvoices(Long userId, Month month);

}
