package com.solchan98.financial_ledger.account.service;

import com.solchan98.financial_ledger.account.domain.AccountRepository;
import com.solchan98.financial_ledger.account.domain.dto.SignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public SignUp.Response signUp(SignUp.Request request) {
        return null;
    }
}
