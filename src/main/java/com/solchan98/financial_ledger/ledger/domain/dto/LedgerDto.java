package com.solchan98.financial_ledger.ledger.domain.dto;

import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.LedgerContent;
import com.solchan98.financial_ledger.config.exception.Message;
import com.solchan98.financial_ledger.ledger.domain.Ledger;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class LedgerDto {

    @Builder
    @Getter
    public static class Request {
        private String content;
        private Long price;
    }

    @Builder
    @Getter
    public static class UpdateRequest {
        private Long id;
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

    @Getter
    @Builder
    public static class SimpleResponse {
        private Long id;
        private String content;
        private Long price;
        private LocalDate writeAt;

        public static LedgerDto.SimpleResponse getLedgerSimpleResponse(Ledger ledger) {
            return SimpleResponse.builder()
                    .id(ledger.getId())
                    .content(ledger.getContent())
                    .price(ledger.getPrice())
                    .writeAt(ledger.getWriteAt())
                    .build();
        }
    }
    @Getter
    @Builder
    public static class ListResponse {
        private List<SimpleResponse> data;
        private Message message;

        public static LedgerDto.ListResponse getLedgerSimpleResponse(List<SimpleResponse> data) {
            return ListResponse.builder()
                    .data(data)
                    .message(Message.builder().msg(LedgerContent.GET_LEDGER_LIST_OK).status(Status.LEDGER_OK).build())
                    .build();
        }
    }

}
