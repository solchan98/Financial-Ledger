package com.solchan98.financial_ledger.account.domain.dto;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.AccountContent;
import com.solchan98.financial_ledger.config.Message;
import lombok.Builder;
import lombok.Getter;

public class Login {
    @Getter
    @Builder
    public static class Request {
        private String email;
        private String password;
    }

    @Getter
    @Builder
    public static class Response {
        private Long id;
        private String email;
        private String name;
        private String accessToken;
        private String refreshToken;
        private Message message;

        public static Login.Response getLoginResponse(Account account, String accessToken, String refreshToken) {
            return Response.builder()
                    .id(account.getId())
                    .name(account.getName())
                    .email(account.getEmail())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .message(Message.builder().msg(AccountContent.LOGIN_OK).status(Status.LOGIN_OK).build())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class RefreshToken {
        private String accessToken;
        private String refreshToken;
        private Message message;
        public static Login.RefreshToken getNewTokenResponse(String accessToken, String refreshToken) {
            return RefreshToken.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .message(Message.builder()
                            .msg(AccountContent.TOKEN_REISSUE_OK)
                            .status(Status.TOKEN_REISSUE_OK).build())
                    .build();
        }
    }
}
