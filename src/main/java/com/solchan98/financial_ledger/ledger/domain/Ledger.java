package com.solchan98.financial_ledger.ledger.domain;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.trashBasketHistory.domain.TrashBasketHistory;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter

public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @OneToMany(mappedBy = "ledger", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<TrashBasketHistory> historyList = new ArrayList<>();

    private LocalDate writeAt;

    private Long price;

    private String content;

    private Boolean isDelete;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
