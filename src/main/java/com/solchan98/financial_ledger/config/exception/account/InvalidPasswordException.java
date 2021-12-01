package com.solchan98.financial_ledger.config.exception.account;

import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.AccountContent;
import lombok.Getter;

@Getter
public class InvalidPasswordException extends RuntimeException{
    private final Status status;
    public InvalidPasswordException(){
        super(AccountContent.SIGN_UP_PASSWORD_INVALID);
        this.status = Status.SIGN_UP_BAD_REQUEST;
    }
}
