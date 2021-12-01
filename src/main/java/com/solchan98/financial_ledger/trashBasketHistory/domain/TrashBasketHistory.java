package com.solchan98.financial_ledger.trashBasketHistory.domain;

import com.solchan98.financial_ledger.ledger.domain.Ledger;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
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
}
