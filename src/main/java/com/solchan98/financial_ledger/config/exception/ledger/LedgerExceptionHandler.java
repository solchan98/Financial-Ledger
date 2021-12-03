package com.solchan98.financial_ledger.config.exception.ledger;

import com.solchan98.financial_ledger.config.exception.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LedgerExceptionHandler {

    @ExceptionHandler(BadRequestCreateLedgerException.class)
    public ResponseEntity<Message> handle(BadRequestCreateLedgerException e){
        Message message = Message.builder().msg(e.getMessage()).status(e.getStatus()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
