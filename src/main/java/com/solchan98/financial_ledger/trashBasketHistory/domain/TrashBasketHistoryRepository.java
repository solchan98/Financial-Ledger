package com.solchan98.financial_ledger.trashBasketHistory.domain;

import com.solchan98.financial_ledger.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrashBasketHistoryRepository extends JpaRepository<TrashBasketHistory, Long> {
    List<TrashBasketHistory> findAllByLedgerAccountAndType(Account account, HistoryType type);
    List<TrashBasketHistory> findAllByLedgerAccount(Account account);
}
