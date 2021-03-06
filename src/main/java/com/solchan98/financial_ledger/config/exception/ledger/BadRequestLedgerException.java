package com.solchan98.financial_ledger.config.exception.ledger;

import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.LedgerContent;
import lombok.Getter;

@Getter
public class BadRequestLedgerException extends RuntimeException{
    private final Status status;
    public BadRequestLedgerException(){
        super(LedgerContent.LEDGER_BAD_REQUEST);
        this.status = Status.LEDGER_BAD_REQUEST;
    }
}
