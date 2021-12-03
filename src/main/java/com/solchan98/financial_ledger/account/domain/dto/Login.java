package com.solchan98.financial_ledger.account.domain.dto;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.config.Status;
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
        private Status status;

        public static Login.Response getLoginResponse(Account account, String accessToken, String refreshToken) {
            return Response.builder()
                    .id(account.getId())
                    .name(account.getName())
                    .email(account.getEmail())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .status(Status.LOGIN_OK)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class RefreshToken {
        private String accessToken;
        private String refreshToken;
        private Status status;
        public static Login.RefreshToken getNewTokenResponse(String accessToken, String refreshToken) {
            return RefreshToken.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .status(Status.REFRESH_TOKEN_REISSUE_OK)
                    .build();
        }
    }
}
