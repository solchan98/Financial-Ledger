package com.solchan98.financial_ledger.trashBasketHistory.domain;

import com.solchan98.financial_ledger.account.domain.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrashBasketHistoryRepository extends JpaRepository<TrashBasketHistory, Long> {
    @EntityGraph(attributePaths = "ledger")
    List<TrashBasketHistory> findAllByLedgerAccountAndType(Account account, HistoryType type);
    @EntityGraph(attributePaths = "ledger")
    List<TrashBasketHistory> findAllByLedgerAccount(Account account);
}
