package com.solchan98.financial_ledger.config.exception.ledger;

import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;

@RestControllerAdvice
public class LedgerExceptionHandler {

    @ExceptionHandler(BadRequestCreateLedgerException.class)
    public ResponseEntity<Message> handle(BadRequestCreateLedgerException e){
        Message message = Message.builder().msg(e.getMessage()).status(e.getStatus()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestLedgerException.class)
    public ResponseEntity<Message> handle(BadRequestLedgerException e){
        Message message = Message.builder().msg(e.getMessage()).status(e.getStatus()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<Message> handle(DateTimeException e){
        Message message = Message.builder().msg(e.getMessage()).status(Status.BAD_REQUEST).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
