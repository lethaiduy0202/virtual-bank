package com.virtualbank.services;

import java.util.List;
import com.virtualbank.domain.AccountSavedResponse;
import com.virtualbank.domain.TransactionHistoryResponse;
import com.virtualbank.dto.InfoTranferDto;
import com.virtualbank.dto.TransactionTypeDto;
import com.virtualbank.exceptions.AccountException;

public interface TransactionService {

  void saveTransactionType(TransactionTypeDto transType);

  void tranferMoneny(Long userId, InfoTranferDto infoTranfer) throws AccountException;
  
  void payBills(Long userId, List<InfoTranferDto> infoTranfers) throws AccountException;
  
  List<TransactionHistoryResponse> getTransHistory(Long userId)  throws AccountException; 
  
  List<AccountSavedResponse> getAccountsSaved(Long userId) throws AccountException;

}
