package com.solchan98.financial_ledger.config.exception.ledger;

import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.LedgerContent;
import lombok.Getter;

@Getter
public class BadRequestCreateLedgerException extends RuntimeException{
    private final Status status;
    public BadRequestCreateLedgerException(){
        super(LedgerContent.LEDGER_DATA_BAD_REQUEST);
        this.status = Status.LEDGER_BAD_REQUEST;
    }
}
