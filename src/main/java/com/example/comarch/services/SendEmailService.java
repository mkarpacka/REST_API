package com.example.comarch.services;

import com.example.comarch.entities.Account;

public interface SendEmailService {

    void sendConfirmingTransferEmail(String firstAccountNumber, String secondAccountNumber, String email);

}
