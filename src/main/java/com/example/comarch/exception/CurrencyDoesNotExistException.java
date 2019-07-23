package com.example.comarch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CurrencyDoesNotExistException extends Exception{
    public CurrencyDoesNotExistException(String errorMessage){
        super(errorMessage);
    }
}
