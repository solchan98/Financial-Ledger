package com.solchan98.financial_ledger.config.exception.account;

import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.AccountContent;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{
    private final Status status;
    public UserNotFoundException(){
        super(AccountContent.USER_NOT_FOUND);
        this.status = Status.USER_NOT_FOUND;
    }
}
