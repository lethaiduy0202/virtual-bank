package com.virtualbank.services.impl;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.virtualbank.domain.InvoicesDetailResponse;
import com.virtualbank.domain.InvoicesRepsonse;
import com.virtualbank.dto.InvoicesDto;
import com.virtualbank.dto.InvoicesTypeDto;
import com.virtualbank.entity.Invoices;
import com.virtualbank.entity.InvoicesType;
import com.virtualbank.entity.User;
import com.virtualbank.enums.ErrorsEnum;
import com.virtualbank.exceptions.InvoicesException;
import com.virtualbank.mapper.InvoicesMapper;
import com.virtualbank.mapper.InvoicesTypeMapper;
import com.virtualbank.repositories.InvoicesRepository;
import com.virtualbank.repositories.InvoicesTypeRepository;
import com.virtualbank.services.InvoicesService;
import com.virtualbank.services.UserService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InvoicesServiceImpl implements InvoicesService {
  private BigDecimal totalConsumption;
  
  @Autowired
  private InvoicesTypeRepository invoicesTypeRepository;

  @Autowired
  private InvoicesRepository invoicesRepository;

  @Autowired
  private UserService userService;
  

  @Override
  public void saveInvoicesType(InvoicesTypeDto invoicesDto) {
    try {
      invoicesTypeRepository.save(InvoicesTypeMapper.convertToEntity(invoicesDto));

    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  @Override
  public void saveInvoices(Long userId, InvoicesDto invoicesDto) throws InvoicesException {
    try {
      User user = userService.getUserByUserId(userId);
      Optional<InvoicesType> invoicesTypeOpt =
          invoicesTypeRepository.findByInvoicesType(invoicesDto.getInvoicesTypes().name());
      if (invoicesTypeOpt.isPresent()) {
        invoicesRepository
            .save(InvoicesMapper.convertToEntity(invoicesDto, user, invoicesTypeOpt.get()));
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new InvoicesException(ErrorsEnum.CANNOT_SAVE.getErrorMessage());
    }

  }

  @Override
  public InvoicesRepsonse getInvoices(Long userId, Month month) {
    User user = userService.getUserByUserId(userId);
    List<InvoicesDetailResponse> invoicesDetails = new ArrayList<>();
    totalConsumption =  new BigDecimal(0);
    List<Invoices> invoices = invoicesRepository.findByUserAndBillMonth(user, month.name());
    invoices.stream().forEach(invoice -> {
      InvoicesDetailResponse invoicesDetail =
          InvoicesDetailResponse.builder().id(invoice.getId()).consumption(invoice.getConsumption())
              .billAmount(invoice.getBillAmount()).invoicesType(invoice.getInvoicesType()).dayPay(invoice.getDatePay()).build();
      invoicesDetails.add(invoicesDetail);
      totalConsumption = totalConsumption.add(invoice.getBillAmount());
    });
    return InvoicesRepsonse.builder().userId(user.getId()).month(month)
        .invoiceDetails(invoicesDetails).totalConsumption(totalConsumption).build();
  }

}
