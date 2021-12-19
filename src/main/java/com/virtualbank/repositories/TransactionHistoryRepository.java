package com.virtualbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.virtualbank.entity.TransactionHistory;

public interface TransactionHistoryRepository  extends JpaRepository<TransactionHistory, Long>{

}
