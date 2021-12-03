package com.solchan98.financial_ledger.config.exception.account;

import com.solchan98.financial_ledger.config.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorMessage> handle(InvalidEmailException e){
        ErrorMessage message = ErrorMessage.builder().msg(e.getMessage()).status(e.getStatus()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorMessage> handle(InvalidPasswordException e){
        ErrorMessage message = ErrorMessage.builder().msg(e.getMessage()).status(e.getStatus()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorMessage> handle(DuplicateEmailException e){
        ErrorMessage message = ErrorMessage.builder().msg(e.getMessage()).status(e.getStatus()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginBadRequestException.class)
    public ResponseEntity<ErrorMessage> handle(LoginBadRequestException e){
        ErrorMessage message = ErrorMessage.builder().msg(e.getMessage()).status(e.getStatus()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


}
