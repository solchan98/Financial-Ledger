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
    TOKEN_REISSUE_OK(200, "TOKEN_REISSUE_OK"),
    LEDGER_OK(200, "LEDGER_OK"),
    LEDGER_BAD_REQUEST(400, "LEDGER_BAD_REQUEST"),
    DELETE_LEDGER_OK(200, "DELETE_LEDGER_OK"),
    RESTORE_LEDGER_OK(200, "RESTORE_LEDGER_OK"),
    HISTORY_OK(200, "HISTORY_OK");

    int statusCode;
    String code;

    Status(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
