package com.example.comarch.services;

import com.example.comarch.entities.Account;
import com.example.comarch.exception.AccountDoesNotExistException;
import com.example.comarch.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Account getOneAccount(String number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(String number, Account account) throws AccountDoesNotExistException {
        Account updatedAccount = accountRepository.findByNumber(number);

        if(updatedAccount == null) throw new AccountDoesNotExistException("not exist");

        if(account.getCurrency()!=null) updatedAccount.setCurrency(account.getCurrency());
        if(account.getMoney()!=null) updatedAccount.setMoney(account.getMoney());
        if(account.getOwner()!=null) updatedAccount.setOwner(account.getOwner());

//        updatedAccount.setNumber(account.getNumber());
        accountRepository.save(updatedAccount);
        return updatedAccount;
    }

    @Override
    public void deleteAccount(String number) throws AccountDoesNotExistException {
        Account accountToDelete = accountRepository.findByNumber(number);
        List<Account> accounts = accountRepository.findAll();

        if (accounts.contains(accountToDelete)) {
            accountRepository.delete(accountToDelete);

        }else throw new AccountDoesNotExistException("name");
    }

}

