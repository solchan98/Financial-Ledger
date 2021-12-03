package com.solchan98.financial_ledger.config.exception.account;

import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.AccountContent;
import lombok.Getter;

@Getter
public class LoginBadRequestException extends RuntimeException{
    private final Status status;
    public LoginBadRequestException(){
        super(AccountContent.LOGIN_UP_INVALID);
        this.status = Status.LOGIN_BAD_REQUEST;
    }
}
