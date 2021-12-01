package com.solchan98.financial_ledger.account;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.account.domain.dto.SignUp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AccountTemplate {

    public static final String EMAIL = "sol@gmail.com";
    public static final String INVALID_EMAIL = "sol";
    public static final String PASSWORD = "a1234567";
    public static final String INVALID_PASSWORD = "a1234";
    public static final String NAME = "solchan";

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static Account makeTestAccount(){
        return Account.builder().name(NAME).email(EMAIL).password(bCryptPasswordEncoder.encode(PASSWORD)).build();
    }

    public static SignUp.Request makeSignUpAccount(){
        return SignUp.Request
                .builder().name(NAME).email(EMAIL).password(PASSWORD).build();
    }

    public static SignUp.Request makeSignUpInvalidEmail(){
        return SignUp.Request
                .builder().name(NAME).email(INVALID_EMAIL).password(PASSWORD).build();
    }

    public static SignUp.Request makeSignUpInvalidPassword(){
        return SignUp.Request
                .builder().name(NAME).email(EMAIL).password(INVALID_PASSWORD).build();
    }

}
