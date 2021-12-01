package com.solchan98.financial_ledger.account;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.account.domain.AccountRepository;
import com.solchan98.financial_ledger.account.domain.dto.SignUp;
import com.solchan98.financial_ledger.account.service.AccountService;
import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.exception.account.DuplicateEmailException;
import com.solchan98.financial_ledger.config.exception.account.InvalidEmailException;
import com.solchan98.financial_ledger.config.exception.account.InvalidPasswordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.solchan98.financial_ledger.account.AccountTemplate.*;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName("회원가입 성공")
    void signUpSuccess() {
        // given
        Account account = makeTestAccount();
        SignUp.Request signUpRequest = makeSignUpAccount();
        given(accountRepository.save(any())).willReturn(account);
        // when
        SignUp.Response signUpResponse = accountService.signUp(signUpRequest);
        // then
        assertAll(
                () ->assertEquals(account.getEmail(), signUpResponse.getEmail()),
                () ->assertEquals(account.getPassword(), signUpResponse.getPassword()),
                () ->assertEquals(account.getName(), signUpResponse.getName()),
                () -> assertEquals(Status.SIGN_UP_OK, signUpResponse.getStatus())
        );
    }

    @Test
    @DisplayName("회원가입 실패 - 이메일 중복")
    void signUpFailByEmailDuplicate() {
        // given
        Account account = makeTestAccount();
        SignUp.Request signUpRequest = makeSignUpAccount();
        given(accountRepository.existsByEmail(account.getEmail())).willReturn(true);
        // when then
        assertThrows(InvalidEmailException.class, () -> accountService.signUp(signUpRequest));
    }

    @Test
    @DisplayName("회원가입 실패 - 이메일 형식 오류")
    void signUpFailByEmailInvalid() {
        // given
        SignUp.Request signUpInvalidEmail = makeSignUpInvalidEmail();
        // when then
        assertThrows(DuplicateEmailException.class, () -> accountService.signUp(signUpInvalidEmail));
    }

    @Test
    @DisplayName("회원가입 실패 - 비밀번호 형식 오류")
    void signUpFailByPasswordInvalid() {
        // given
        SignUp.Request signUpInvalidPassword = makeSignUpInvalidPassword();
        // when then
        assertThrows(InvalidPasswordException.class, () -> accountService.signUp(signUpInvalidPassword));
    }
}
