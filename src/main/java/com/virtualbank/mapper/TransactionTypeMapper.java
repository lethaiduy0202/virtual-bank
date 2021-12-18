package com.virtualbank.mapper;

import com.virtualbank.dto.TransactionTypeDto;
import com.virtualbank.entity.TransactionType;

public class TransactionTypeMapper {
  private TransactionTypeMapper() {}

  public static TransactionType convertToEntity(TransactionTypeDto transactionTypeDto) {
    return TransactionType.builder().transType(transactionTypeDto.getTransType().name()).build();
  }

}
