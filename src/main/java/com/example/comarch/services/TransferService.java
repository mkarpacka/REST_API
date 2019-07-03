package com.example.comarch.services;

import com.example.comarch.entities.Account;
import com.example.comarch.entities.Transfer;

import java.util.List;

public interface TransferService {

    List<Account> makeTransfer(Account firstAccount, Account secondAccount, Double valueOfTransfer);
    List<Transfer> getAllTransfers();
    List<Transfer> getAccountTransfers(Long number);
    Transfer addTransfer(Transfer transfer);
}
