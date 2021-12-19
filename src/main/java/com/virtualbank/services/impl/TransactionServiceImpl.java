package com.virtualbank.services.impl;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.virtualbank.dto.InfoTranferDto;
import com.virtualbank.dto.TransactionTypeDto;
import com.virtualbank.entity.Account;
import com.virtualbank.entity.TransactionHistory;
import com.virtualbank.entity.TransactionType;
import com.virtualbank.entity.User;
import com.virtualbank.enums.ErrorsEnum;
import com.virtualbank.enums.TransTypesEnum;
import com.virtualbank.exceptions.AccountException;
import com.virtualbank.mapper.TransactionTypeMapper;
import com.virtualbank.repositories.AccountRepository;
import com.virtualbank.repositories.TransactionHistoryRepository;
import com.virtualbank.repositories.TransactionTypeRepository;
import com.virtualbank.services.TransactionService;
import com.virtualbank.services.UserService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  private TransactionTypeRepository transactionTypeRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private TransactionHistoryRepository transactionHistoryRepository;

  @Autowired
  private UserService userService;

  @Override
  public void saveTransactionType(TransactionTypeDto transType) {
    try {
      transactionTypeRepository.save(TransactionTypeMapper.convertToEntity(transType));
    } catch (Exception e) {
      log.error(e.getMessage());
    }

  }

  @Override
  @Transactional
  public void tranferMoneny(Long userId, InfoTranferDto infoTranfer) throws AccountException {
    try {
      log.info("Starting tranfer money for receiver  {}", infoTranfer.getUsername());
      Optional<Account> receiverAccountOpt =
          accountRepository.findByAccNumber(infoTranfer.getAccounReceiver());
      if (receiverAccountOpt.isPresent()) {
        // add money for receiver
        receiverAccountOpt.get()
            .setAccBalance(receiverAccountOpt.get().getAccBalance().add(infoTranfer.getAmount()));
        accountRepository.save(receiverAccountOpt.get());

        // build transaction history - receiver
        Optional<TransactionType> receiverTransTypeOpt =
            transactionTypeRepository.findByTransType(TransTypesEnum.ADD.name());
        TransactionHistory receiverTransHistory = TransactionHistory.builder()
            .account(receiverAccountOpt.get()).user(receiverAccountOpt.get().getUser())
            .transType(receiverTransTypeOpt.get()).transNote(infoTranfer.getContent())
            .transAmount(infoTranfer.getAmount()).transDate(new Date()).build();
        transactionHistoryRepository.save(receiverTransHistory);


        User sender = userService.getUserByUserId(userId);
        Optional<Account> senderAccountOpt = accountRepository.findByUser(sender);
        if (senderAccountOpt.isPresent()) {
          // minus for sender;
          senderAccountOpt.get().setAccBalance(
              senderAccountOpt.get().getAccBalance().subtract(infoTranfer.getAmount()));
          accountRepository.save(senderAccountOpt.get());

          // build transaction history - sender
          Optional<TransactionType> senderTransTypeOpt =
              transactionTypeRepository.findByTransType(TransTypesEnum.MINUS.name());
          TransactionHistory senderTransHistory = TransactionHistory.builder()
              .account(senderAccountOpt.get()).user(senderAccountOpt.get().getUser())
              .transType(senderTransTypeOpt.get()).transNote(infoTranfer.getContent())
              .transAmount(infoTranfer.getAmount()).transDate(new Date()).build();
          transactionHistoryRepository.save(senderTransHistory);

          log.info("Tranfer money for receiver  {} success", infoTranfer.getUsername());
        }

      } else {
        throw new AccountException(ErrorsEnum.ACCOUNT_NON_EXIST.getErrorMessage());
      }


    } catch (Exception e) {
      log.error(e.getMessage());
    }

  }

}
