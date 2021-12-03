package com.solchan98.financial_ledger.ledger.domain;

import com.solchan98.financial_ledger.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {
    Optional<Ledger> findByIdAndIsDeleteIsFalse(Long id);
    Optional<Ledger> findByIdAndIsDeleteIsTrue(Long id);
    List<Ledger> findAllByAccountAndIsDeleteIsFalse(Account account);
}
