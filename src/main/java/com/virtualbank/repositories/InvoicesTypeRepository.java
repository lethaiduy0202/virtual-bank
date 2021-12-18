package com.virtualbank.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.virtualbank.entity.InvoicesType;

@Repository
public interface InvoicesTypeRepository extends JpaRepository<InvoicesType, Long> {
  
  Optional<InvoicesType> findByInvoicesType(String invoicesType);

}
