package com.solchan98.financial_ledger.account;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.account.domain.AccountRepository;
import com.solchan98.financial_ledger.account.domain.dto.SignUp;
import com.solchan98.financial_ledger.account.service.AccountService;
import com.solchan98.financial_ledger.config.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.solchan98.financial_ledger.account.AccountTemplate.makeSignUpAccount;
import static com.solchan98.financial_ledger.account.AccountTemplate.makeTestAccount;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    static Account account = makeTestAccount();
    static SignUp.Request signUpRequest = makeSignUpAccount();

    @Test
    @DisplayName("회원가입 성공")
    void signUpSuccess() {
        // given
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

    }

    @Test
    @DisplayName("회원가입 실패 - 이메일 형식 오류")
    void signUpFailByEmailInvalid() {

    }

    @Test
    @DisplayName("회원가입 실패 - 비밀번호 형식 오류")
    void signUpFailByPasswordInvalid() {

    }
}
