package com.example.comarch.repository;

import com.example.comarch.entities.Account;
import com.example.comarch.entities.ExternalTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalTransferRepository extends JpaRepository<ExternalTransfer, Long> {
}
