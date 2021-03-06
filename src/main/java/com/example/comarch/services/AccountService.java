package com.example.comarch.services;

import com.example.comarch.entities.Account;
import com.example.comarch.exception.AccountDoesNotExistException;
import com.example.comarch.exception.CurrencyDoesNotExistException;

import java.util.List;

public interface AccountService {

    List<Account> getAccounts();

    Account getOneAccount(String number) throws AccountDoesNotExistException;

    Account getOneAccountById(Long id) throws AccountDoesNotExistException;

    Account addAccount(Account account) throws CurrencyDoesNotExistException;

    Account updateAccount(String number, Account account) throws AccountDoesNotExistException;

    void deleteAccount(String number) throws AccountDoesNotExistException;

    Account findByNumber(String accountNumber) throws AccountDoesNotExistException;

}
