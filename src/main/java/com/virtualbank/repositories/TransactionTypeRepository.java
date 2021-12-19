package com.virtualbank.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.virtualbank.entity.TransactionType;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
  Optional<TransactionType> findByTransType(String transType);
}
