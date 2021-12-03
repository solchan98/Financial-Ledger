package com.solchan98.financial_ledger.config.exception.account;

import com.solchan98.financial_ledger.config.exception.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Message> handle(InvalidEmailException e){
        Message message = Message.builder().msg(e.getMessage()).status(e.getStatus()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Message> handle(InvalidPasswordException e){
        Message message = Message.builder().msg(e.getMessage()).status(e.getStatus()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Message> handle(DuplicateEmailException e){
        Message message = Message.builder().msg(e.getMessage()).status(e.getStatus()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginBadRequestException.class)
    public ResponseEntity<Message> handle(LoginBadRequestException e){
        Message message = Message.builder().msg(e.getMessage()).status(e.getStatus()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RefreshTokenBadRequestException.class)
    public ResponseEntity<Message> handle(RefreshTokenBadRequestException e){
        Message message = Message.builder().msg(e.getMessage()).status(e.getStatus()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


}
