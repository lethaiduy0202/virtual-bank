package com.virtualbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.virtualbank.entity.TransactionType;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionType, Long> {

}
