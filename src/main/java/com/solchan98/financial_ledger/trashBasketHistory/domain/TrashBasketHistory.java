package com.solchan98.financial_ledger.trashBasketHistory.domain;

import com.solchan98.financial_ledger.ledger.domain.Ledger;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "trash_basket_history")
public class TrashBasketHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ledger ledger;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private HistoryType type;

    @Builder
    public TrashBasketHistory(Ledger ledger, HistoryType type) {
        this.ledger = ledger;
        this.date = LocalDateTime.now();
        this.type = type;
    }

    public void addLedger(Ledger ledger) {
        this.ledger = ledger;
    }
}
