package com.example.BankinApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BankinApp.Entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
