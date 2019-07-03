package com.example.comarch.services;

import com.example.comarch.entities.Account;
import com.example.comarch.exception.AccountDoesNotExistException;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAccounts();
    Account getOneAccount(String number);
    Account addAccount(Account account);
    Account updateAccount(String number, Account account) throws AccountDoesNotExistException;
    void deleteAccount(String number) throws AccountDoesNotExistException;


}
