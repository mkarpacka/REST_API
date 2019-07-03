package com.example.comarch.services;

import com.example.comarch.entities.Account;
import com.example.comarch.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private AccountRepository accountRepository;

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getOneAccount(Long number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long number, Account account) {
        Account updatedAccount = accountRepository.findByNumber(number);

        updatedAccount.setCurrency(account.getCurrency());
        updatedAccount.setMoney(account.getMoney());
        updatedAccount.setOwner(account.getOwner());
//        updatedAccount.setNumber(account.getNumber());
        accountRepository.save(updatedAccount);
        return updatedAccount;
    }

    @Override
    public boolean deleteAccount(Long number) {
        Account accountToDelete = accountRepository.findByNumber(number);
        List<Account> accounts = accountRepository.findAll();

        if (accounts.contains(accountToDelete)) {
            accountRepository.delete(accountToDelete);
            return true;
        }
        return false;
    }

}

