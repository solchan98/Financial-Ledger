package com.solchan98.financial_ledger.ledger.domain;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.trashBasketHistory.domain.TrashBasketHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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

    @Column(columnDefinition = "TEXT")
    private String content;

    private Boolean isDelete;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @Builder
    public Ledger(Account account, String content, Long price) {
        this.account = account;
        this.writeAt = LocalDate.now();
        this.price = price;
        this.content = content;
        this.isDelete = false;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public void addTrashBasket(TrashBasketHistory history) {
        this.historyList.add(history);
        history.addLedger(this);
    }

    public void updateLedger(String content, Long price) {
        this.content = content;
        this.price = price;
        this.updateAt = LocalDateTime.now();
    }

    public void deleteLedger() {
        this.isDelete = true;
    }
    public void restoreLedger() {
        this.isDelete = false;
    }
}
