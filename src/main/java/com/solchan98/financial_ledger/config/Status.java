package com.solchan98.financial_ledger.config;

public enum Status {
    BAD_REQUEST(400, "BAD_REQUEST"),
    USER_NOT_FOUND(404, "USER_NOT_FOUND"),
    SIGN_UP_OK(200, "SIGN_UP_OK"),
    SIGN_UP_BAD_REQUEST(400, "SIGN_UP_BAD_REQUEST"),
    LOGIN_OK(200, "LOGIN_OK"),
    LOGOUT_OK(200, "LOGOUT_OK"),
    LOGIN_BAD_REQUEST(400, "LOGIN_BAD_REQUEST"),
    ACCESS_TOKEN_INVALID(403, "ACCESS_TOKEN_INVALID"),
    REFRESH_TOKEN_REISSUE_OK(200, "REFRESH_TOKEN_REISSUE_OK"),
    CREATE_LEDGER_OK(200, "CREATE_LEDGER_OK"),
    CREATE_LEDGER_BAD_REQUEST(400, "CREATE_LEDGER_BAD_REQUEST");

    int statusCode;
    String code;

    Status(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
