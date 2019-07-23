package com.example.comarch.services;

import com.example.comarch.entities.Account;
import com.example.comarch.entities.Transfer;
import com.example.comarch.exception.AccountDoesNotExistException;
import com.example.comarch.exception.CurrencyDoesNotExistException;

import java.util.List;

public interface TransferService {

    List<Account> makeTransfer(String firstAccountNumber, String secondAccountNumber, Double valueOfTransfer) throws AccountDoesNotExistException, CurrencyDoesNotExistException;

    List<Transfer> getAllTransfers();

    List<Transfer> getAccountTransfers(String number) throws AccountDoesNotExistException;

    List<Transfer> getIncomingAccountTransfers(String number) throws AccountDoesNotExistException;

    List<Transfer> getOutgoingAccountTransfers(String number) throws AccountDoesNotExistException;

    Transfer addTransfer(Transfer transfer);

    Double currencyConverter(Account firstAccount, Account secondAccount, Double valueOfTransfer) throws CurrencyDoesNotExistException;

    void changeTransferStatus();

    void updateTransferWithNewMoneyOnSecondAccount(Double newMoneyToUpdateOnSecondAccount, Account account);
}
