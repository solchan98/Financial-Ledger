package com.solchan98.financial_ledger.account.domain.dto;

import com.solchan98.financial_ledger.config.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class SignUp {

    @Getter
    @Builder
    public static class Request {
        private String email;
        private String name;
        private String password;
    }

    @Getter
    @Builder
    public static class Response {
        private Long id;
        private String email;
        private String name;
        private String password;
        private LocalDateTime loginAt;
        private Status status;
    }

}
