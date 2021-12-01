package com.solchan98.financial_ledger.config.exception.account;

import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.AccountContent;
import lombok.Getter;

@Getter
public class InvalidEmailException extends RuntimeException{
    private final Status status;
    public InvalidEmailException(){
        super(AccountContent.SIGN_UP_EMAIL_INVALID);
        this.status = Status.SIGN_UP_BAD_REQUEST;
    }
}
