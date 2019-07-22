package com.example.comarch.repository;

import com.example.comarch.entities.Transfer;
import com.example.comarch.entities.enums.TransferStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    List<Transfer> findByFirstAccountNumber(String accountNumber);

    List<Transfer> findBySecondAccountNumber(String accountNumber);

    List<Transfer> findByTransferStatus(TransferStatus transferStatus);
}