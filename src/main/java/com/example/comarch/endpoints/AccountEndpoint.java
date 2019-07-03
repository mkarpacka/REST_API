package com.example.comarch.endpoints;

import com.example.comarch.entities.Account;
import com.example.comarch.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class AccountEndpoint {

    private AccountService accountService;


    @Autowired
    public AccountEndpoint(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("accounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("accounts/get-account/{number}")
    public ResponseEntity<?> getOneAccount(@PathVariable Long number) {
        Account account = accountService.getOneAccount(number);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("accounts/add")
    public ResponseEntity<?> addAccount(@RequestBody Account account) {
        accountService.addAccount(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("accounts/update/{number}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long number, @RequestBody Account account) {

        accountService.updateAccount(number, account);

        return new ResponseEntity<>(account, HttpStatus.OK);
    }


    @DeleteMapping("accounts/delete/{number}")
    public ResponseEntity<Account> deleteAccount(@PathVariable Long number) {

        if (accountService.deleteAccount(number)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
