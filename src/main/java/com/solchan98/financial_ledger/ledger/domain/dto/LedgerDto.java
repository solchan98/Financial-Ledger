package com.solchan98.financial_ledger.ledger.domain.dto;

import com.solchan98.financial_ledger.config.exception.Message;
import com.solchan98.financial_ledger.ledger.domain.Ledger;
import lombok.Builder;
import lombok.Getter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LedgerDto {

    @Builder
    @Getter
    public static class Request {
        private String content;
        private Long price;
    }

    @Getter
    @Builder
    public static class Response {
        private Long id;
        private LocalDate writeAt;
        private String content;
        private Long price;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
        private Message message;

        public static LedgerDto.Response getLedgerResponse(Ledger ledger, Message message) {
            return Response.builder()
                    .id(ledger.getId())
                    .writeAt(ledger.getWriteAt()).createAt(ledger.getCreateAt()).updateAt(ledger.getUpdateAt())
                    .content(ledger.getContent())
                    .price(ledger.getPrice())
                    .message(message)
                    .build();
        }
    }
}
