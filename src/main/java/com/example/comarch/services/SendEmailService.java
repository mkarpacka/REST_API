package com.example.comarch.services;

import com.example.comarch.entities.Account;

public interface SendEmailService {

    void sendConfirmingTransferEmail(Account firstAccount, Account secondAccount, String email);

}
