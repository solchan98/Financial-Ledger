package com.solchan98.financial_ledger.ledger.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {
}
