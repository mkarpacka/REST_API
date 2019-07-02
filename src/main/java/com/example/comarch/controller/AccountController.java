package com.example.comarch.controller;

import com.example.comarch.Account;
import com.example.comarch.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("accounts")
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    @PostMapping("accounts")
    public Account addAccount(@RequestBody Account account){
        return accountRepository.save(account);
    }
}
