package com.example.comarch.repository;

import com.example.comarch.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByNumber(String number);

    Optional<Account> findById(Long id);
}
