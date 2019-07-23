package com.example.comarch.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
class ExceptionHandling {
    private static final Logger LOGGER = LogManager.getLogger(ExceptionHandling.class);

    @ExceptionHandler(CurrencyDoesNotExistException.class)
    public void handleCurrencyException(HttpServletResponse response, CurrencyDoesNotExistException exc) throws IOException {
        LOGGER.error(exc.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
    }

    @ExceptionHandler(AccountDoesNotExistException.class)
    public void handleAccountException(HttpServletResponse response, AccountDoesNotExistException exc) throws IOException {
        LOGGER.error(exc.getMessage());
        response.sendError(HttpStatus.NOT_FOUND.value(), exc.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleConverterErrors(HttpServletResponse response, HttpMessageNotReadableException exc) throws IOException {
        LOGGER.error(exc.getMessage());
        response.sendError(HttpStatus.NOT_FOUND.value(), "Wrong currency");
    }
}
