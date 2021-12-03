package com.solchan98.financial_ledger.config;

public enum Status {
    SIGN_UP_OK(200, "SIGN_UP_OK"),
    SIGN_UP_BAD_REQUEST(400, "SIGN_UP_BAD_REQUEST"),
    LOGIN_OK(200, "LOGIN_OK"),
    LOGIN_BAD_REQUEST(400, "LOGIN_BAD_REQUEST"),
    ACCESS_TOKEN_INVALID(403, "ACCESS_TOKEN_INVALID");

    int statusCode;
    String code;

    Status(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
