package com.solchan98.financial_ledger.account.controller;

import com.solchan98.financial_ledger.account.domain.dto.SignUp;
import com.solchan98.financial_ledger.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/sign-up")
    public SignUp.Response createAccount(@RequestBody SignUp.Request request) {
        return accountService.signUp(request);
    }
}
