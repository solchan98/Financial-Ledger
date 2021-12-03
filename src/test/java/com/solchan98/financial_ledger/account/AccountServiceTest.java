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
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.solchan98.financial_ledger.account.AccountTemplate.*;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("유저 서비스 로직 테스트")
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName("회원가입 성공")
    void signUpSuccess() {
        // given
        Account account = makeTestAccount();
        SignUp.Request signUpRequest = makeSignUpAccount();
        given(accountRepository.save(any())).willReturn(account);
        given(passwordEncoder.encode(any())).willReturn(account.getPassword());
        // when
        SignUp.Response signUpResponse = accountService.signUp(signUpRequest);
        // then
        assertAll(
                () ->assertEquals(account.getEmail(), signUpResponse.getEmail()),
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
        assertThrows(DuplicateEmailException.class, () -> accountService.signUp(signUpRequest));
    }

    @Test
    @DisplayName("회원가입 실패 - 이메일 형식 오류")
    void signUpFailByEmailInvalid() {
        // given
        SignUp.Request signUpInvalidEmail = makeSignUpInvalidEmail();
        // when then
        assertThrows(InvalidEmailException.class, () -> accountService.signUp(signUpInvalidEmail));
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
