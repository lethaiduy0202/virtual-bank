package com.virtualbank.services;

import com.virtualbank.dto.InfoTranferDto;
import com.virtualbank.dto.TransactionTypeDto;
import com.virtualbank.exceptions.AccountException;

public interface TransactionService {

  void saveTransactionType(TransactionTypeDto transType);

  void tranferMoneny(Long userId, InfoTranferDto infoTranfer) throws AccountException;

}
