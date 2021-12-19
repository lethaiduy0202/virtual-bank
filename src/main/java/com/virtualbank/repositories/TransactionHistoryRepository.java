package com.virtualbank.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.virtualbank.entity.Account;
import com.virtualbank.entity.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

  List<TransactionHistory> findByAccount(Account account);

}
