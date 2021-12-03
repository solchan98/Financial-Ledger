package com.solchan98.financial_ledger.config.exception.account;

import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.AccountContent;
import lombok.Getter;

@Getter
public class RefreshTokenBadRequestException extends RuntimeException{
    private final Status status;
    public RefreshTokenBadRequestException(){
        super(AccountContent.EMPTY_TOKEN);
        this.status = Status.BAD_REQUEST;
    }
}
