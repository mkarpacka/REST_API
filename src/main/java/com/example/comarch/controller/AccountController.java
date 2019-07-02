package com.example.comarch.controller;

import com.example.comarch.Account;
import com.example.comarch.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("accounts")
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @PostMapping("accounts")
    public Account addAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PutMapping("accounts/{number}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long number, @RequestBody Account account){

        Optional<Account> optionalAccount  = accountRepository.findByNumber(number);

        if(optionalAccount.isPresent()){
            optionalAccount.get().setCurrency(account.getCurrency());
            optionalAccount.get().setMoney(account.getMoney());
            optionalAccount.get().setCurrency(account.getCurrency());
            optionalAccount.get().setOwner(account.getOwner());

            return new ResponseEntity<>(accountRepository.save(optionalAccount.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("accounts/{number}")
    public ResponseEntity<Account> deleteAccount(@PathVariable Long number, @RequestBody Account account){

        Optional<Account> optionalAccount  = accountRepository.findByNumber(number);

        if(optionalAccount.isPresent()){
            accountRepository.delete(optionalAccount.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
