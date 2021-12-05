package com.solchan98.financial_ledger.account.domain.dto;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.AccountContent;
import com.solchan98.financial_ledger.config.Message;
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
        private LocalDateTime loginAt;
        private Message message;

        public static Response getSignUpResponse(Account account) {
            return Response.builder()
                    .id(account.getId())
                    .name(account.getName())
                    .email(account.getEmail())
                    .loginAt(account.getLoginAt())
                    .message(Message.builder().msg(AccountContent.SIGN_UP_OK).status(Status.SIGN_UP_OK).build())
                    .build();
        }
    }

}
