package com.solchan98.financial_ledger.trashBasketHistory.domain.dto;

import com.solchan98.financial_ledger.ledger.domain.dto.LedgerDto;
import com.solchan98.financial_ledger.trashBasketHistory.domain.HistoryType;
import com.solchan98.financial_ledger.trashBasketHistory.domain.TrashBasketHistory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HistoryResponse {
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
