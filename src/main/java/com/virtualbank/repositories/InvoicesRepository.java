package com.virtualbank.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.virtualbank.entity.Invoices;
import com.virtualbank.entity.User;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Long> {

  List<Invoices> findByUserAndBillMonth(User user, String billMonth);

}
