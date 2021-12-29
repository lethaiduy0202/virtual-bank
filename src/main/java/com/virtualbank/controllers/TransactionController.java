package com.virtualbank.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtualbank.domain.AccountSavedResponse;
import com.virtualbank.domain.TransactionHistoryResponse;
import com.virtualbank.dto.InfoTranferDto;
import com.virtualbank.dto.TransactionInvoicesDto;
import com.virtualbank.dto.TransactionTypeDto;
import com.virtualbank.exceptions.AccountException;
import com.virtualbank.exceptions.TransactionException;
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

  @PostMapping(value = "/tranfer", produces = MediaType.APPLICATION_JSON_VALUE)
  public void tranferMoney(@RequestBody InfoTranferDto infoTranferDto) throws AccountException {
    transactionService.tranferMoneny(userService.getCurrentUserId(), infoTranferDto);
  }

  @PostMapping(value = "/bill", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public void payBill(@RequestBody TransactionInvoicesDto transactionInvoicesDto)
      throws AccountException, TransactionException {
    transactionService.payBills(userService.getCurrentUserId(), transactionInvoicesDto);
  }

  @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TransactionHistoryResponse>> getTransHistory()
      throws AccountException {
    return ResponseEntity.ok(transactionService.getTransHistory(userService.getCurrentUserId()));
  }

  @GetMapping(value = "/account/saved", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AccountSavedResponse>> getListAccountSaved() throws AccountException {
    return ResponseEntity.ok(transactionService.getAccountsSaved(userService.getCurrentUserId()));
  }


}
