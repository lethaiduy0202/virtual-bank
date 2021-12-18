package com.virtualbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtualbank.dto.TransactionTypeDto;
import com.virtualbank.services.TransactionService;
import com.virtualbank.services.UserService;

@RestController
@RequestMapping("/transction")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @Autowired
  private UserService userService;

  @PostMapping(value = "/type", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public void saveInvoicesType(@RequestBody TransactionTypeDto transactionType) {
    transactionService.saveTransactionType(transactionType);
  }

}
