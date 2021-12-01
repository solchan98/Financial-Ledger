package com.solchan98.financial_ledger.account.service;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.account.domain.AccountRepository;
import com.solchan98.financial_ledger.account.domain.dto.SignUp;
import com.solchan98.financial_ledger.config.exception.account.DuplicateEmailException;
import com.solchan98.financial_ledger.config.exception.account.InvalidEmailException;
import com.solchan98.financial_ledger.config.exception.account.InvalidPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

import static com.solchan98.financial_ledger.config.content.AccountContent.EMAIL_REG_EXT;
import static com.solchan98.financial_ledger.config.content.AccountContent.PASSWORD_REG_EXT;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    @Transactional
    public SignUp.Response signUp(SignUp.Request request) {
        checkRequestData(request);
        Account account = Account.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())).build();
        return SignUp.Response.getSignUpResponse(accountRepository.save(account));
    }

    private void checkRequestData(SignUp.Request request) {
        if(!Pattern.matches(EMAIL_REG_EXT, request.getEmail())) {
            throw new InvalidEmailException();
        }
        if(!Pattern.matches(PASSWORD_REG_EXT, request.getPassword())){
            throw new InvalidPasswordException();
        }
        if(accountRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException();
        }
    }
}
