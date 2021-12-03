package com.solchan98.financial_ledger.account.controller;

import com.solchan98.financial_ledger.account.domain.UserAccount;
import com.solchan98.financial_ledger.account.domain.dto.Login;
import com.solchan98.financial_ledger.account.domain.dto.SignUp;
import com.solchan98.financial_ledger.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping("/login")
    public Login.Response login(@RequestBody Login.Request request) {
        return accountService.login(request);
    }

    @GetMapping("/test")
    public String test() {
        UserAccount principal = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getAccount().getEmail();
    }
}
