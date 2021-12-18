package com.virtualbank.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.virtualbank.entity.Account;
import com.virtualbank.entity.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByUser(User user);

  Optional<Account> findByAccNumber(String accNumber);

}
