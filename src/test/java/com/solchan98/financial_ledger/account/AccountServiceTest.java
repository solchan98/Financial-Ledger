package com.solchan98.financial_ledger.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTest {

    @Test
    @DisplayName("회원가입 성공")
    void signUpSuccess() {

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
