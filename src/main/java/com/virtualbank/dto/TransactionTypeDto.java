package com.virtualbank.dto;

import com.virtualbank.enums.TransTypesEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionTypeDto {
 private TransTypesEnum transType;
}
