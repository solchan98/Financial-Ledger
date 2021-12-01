package com.solchan98.financial_ledger.config.content;

public class AccountContent {
    private AccountContent(){}

    public static final String EMAIL_REG_EXT =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_REG_EXT = "^[a-zA-Z0-9]{8,20}";
    public static final String SIGN_UP_OK = "회원가입이 성공하였습니다.";
    public static final String SIGN_UP_EMAIL_INVALID = "이메일 형식을 확인해주세요.";
    public static final String SIGN_UP_EMAIL_DUPLICATE = "이미 가입된 이메일입니다.";
    public static final String SIGN_UP_PASSWORD_INVALID = "비밀번호를 확인해주세요.";
}
