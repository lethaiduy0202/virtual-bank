package com.virtualbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.virtualbank.dto.TransactionTypeDto;
import com.virtualbank.mapper.TransactionTypeMapper;
import com.virtualbank.repositories.TransactionRepository;
import com.virtualbank.services.TransactionService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  private TransactionRepository transactionRepository;

  @Override
  public void saveTransactionType(TransactionTypeDto transType) {
    try {
      transactionRepository.save(TransactionTypeMapper.convertToEntity(transType));
    } catch (Exception e) {
      log.error(e.getMessage());
    }

  }

}
