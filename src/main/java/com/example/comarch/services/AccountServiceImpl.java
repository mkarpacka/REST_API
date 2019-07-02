package com.example.comarch.services;

import com.example.comarch.entities.Account;
import com.example.comarch.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public Optional<Account> getOneAccount(Long number) {
        Optional<Account> optionalAccount = accountRepository.findByNumber(number);
        if (optionalAccount.isPresent()) {
            return optionalAccount;
        }
        return null;
    }

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long number, Account account) {
        Optional<Account> optionalAccount = accountRepository.findByNumber(number);

        if (optionalAccount.isPresent()) {
            optionalAccount.get().setCurrency(account.getCurrency());
            optionalAccount.get().setMoney(account.getMoney());
            optionalAccount.get().setCurrency(account.getCurrency());
            optionalAccount.get().setOwner(account.getOwner());

            return account;
        }
        return null;
    }

    @Override
    public boolean deleteAccount(Long number, Account account) {
        Optional<Account> optionalAccount = accountRepository.findByNumber(number);

        if (optionalAccount.isPresent()) {
            accountRepository.delete(optionalAccount.get());
            return true;
        }
        return false;
    }
}

