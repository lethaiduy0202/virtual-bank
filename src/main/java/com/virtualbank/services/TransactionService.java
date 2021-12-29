package com.virtualbank.services;

import java.util.List;
import com.virtualbank.domain.AccountSavedResponse;
import com.virtualbank.domain.TransactionHistoryResponse;
import com.virtualbank.dto.InfoTranferDto;
import com.virtualbank.dto.TransactionInvoicesDto;
import com.virtualbank.dto.TransactionTypeDto;
import com.virtualbank.exceptions.AccountException;
import com.virtualbank.exceptions.TransactionException;

public interface TransactionService {

  void saveTransactionType(TransactionTypeDto transType);

  void tranferMoneny(Long userId, InfoTranferDto infoTranfer) throws AccountException;

  void payBills(Long userId, TransactionInvoicesDto transactionInvoicesDto)
      throws AccountException, TransactionException;

  List<TransactionHistoryResponse> getTransHistory(Long userId) throws AccountException;

  List<AccountSavedResponse> getAccountsSaved(Long userId) throws AccountException;

}
