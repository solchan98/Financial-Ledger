package com.solchan98.financial_ledger.trashBasketHistory.domain.dto;

import com.solchan98.financial_ledger.config.Message;
import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.HistoryContent;
import com.solchan98.financial_ledger.ledger.domain.dto.LedgerDto;
import com.solchan98.financial_ledger.trashBasketHistory.domain.HistoryType;
import com.solchan98.financial_ledger.trashBasketHistory.domain.TrashBasketHistory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class HistoryDto {

    private List<HistoryResponse> data;
    private Message message;

    public static HistoryDto getHistoryDto(List<HistoryResponse> data) {
        return HistoryDto.builder()
                .data(data)
                .message(Message.builder()
                        .msg(HistoryContent.HISTORY_OK)
                        .status(Status.HISTORY_OK).build()).build();
    }

    @Getter
    public static class HistoryResponse {
        private Long id;
        private LocalDateTime date;
        private HistoryType type;
        private LedgerDto.SimpleResponse ledger;

        @Builder
        public HistoryResponse(TrashBasketHistory history, LedgerDto.SimpleResponse ledger) {
            this.id = history.getId();
            this.type = history.getType();
            this.date = history.getDate();
            this.ledger = ledger;
        }
    }
}
