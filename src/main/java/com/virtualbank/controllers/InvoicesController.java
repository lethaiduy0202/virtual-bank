package com.virtualbank.controllers;

import java.time.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtualbank.domain.InvoicesRepsonse;
import com.virtualbank.dto.InvoicesDto;
import com.virtualbank.dto.InvoicesTypeDto;
import com.virtualbank.exceptions.InvoicesException;
import com.virtualbank.services.InvoicesService;
import com.virtualbank.services.UserService;

@RestController
@RequestMapping("/invoices")
public class InvoicesController {

  @Autowired
  private InvoicesService invoicesService;

  @Autowired
  private UserService userService;

  @PostMapping(value = "/type", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public void saveInvoicesType(@RequestBody InvoicesTypeDto invoicesType) {
    invoicesService.saveInvoicesType(invoicesType);
  }


  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public void saveInvoices(@RequestBody InvoicesDto invoices) throws InvoicesException {
    invoicesService.saveInvoices(userService.getCurrentUserId(), invoices);
  }

  @GetMapping(value = "/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<InvoicesRepsonse> getInvoices(@PathVariable(value = "month") Month month) {
    return ResponseEntity.ok(invoicesService.getInvoices(userService.getCurrentUserId(), month));

  }


}
