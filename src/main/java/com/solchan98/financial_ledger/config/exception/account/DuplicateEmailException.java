package com.solchan98.financial_ledger.config.exception.account;

import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.AccountContent;
import lombok.Getter;

@Getter
public class DuplicateEmailException extends RuntimeException{
    private final Status status;
    public DuplicateEmailException() {
        super(AccountContent.SIGN_UP_EMAIL_DUPLICATE);
        this.status = Status.SIGN_UP_BAD_REQUEST;
    }
}
