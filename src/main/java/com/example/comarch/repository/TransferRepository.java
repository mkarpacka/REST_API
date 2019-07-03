package com.example.comarch.repository;


import com.example.comarch.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    List<Transfer> findByFirstAccountNumber(Long accountNumber);
    List<Transfer> findBySecondAccountNumber(Long accountNumber);
}
