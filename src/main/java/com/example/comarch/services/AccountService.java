package com.example.comarch.services;

import com.example.comarch.entities.Account;
import com.example.comarch.exception.AccountDoesNotExistException;
import java.util.List;

public interface AccountService {

    List<Account> getAccounts();
    Account getOneAccount(String number);
    Account getOneAccountById(Long id);
    Account addAccount(Account account);
    Account updateAccount(String number, Account account) throws AccountDoesNotExistException;
    void deleteAccount(String number) throws AccountDoesNotExistException;

    Account findByNumber(String accountNumber) throws AccountDoesNotExistException;

}
