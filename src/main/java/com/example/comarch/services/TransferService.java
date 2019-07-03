package com.example.comarch.services;

import com.example.comarch.entities.Account;
import com.example.comarch.entities.Transfer;
import com.example.comarch.exception.AccountDoesNotExistException;

import java.util.List;

public interface TransferService {

    List<Account> makeTransfer(Account firstAccount, Account secondAccount, Double valueOfTransfer) throws AccountDoesNotExistException;
    List<Transfer> getAllTransfers();
    List<Transfer> getAccountTransfers(String number) throws AccountDoesNotExistException;
    Transfer addTransfer(Transfer transfer);
}
