package com.example.comarch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AccountDoesNotExistException extends Exception {
    public AccountDoesNotExistException(String errorMessage){
        super(errorMessage);
    }
}
