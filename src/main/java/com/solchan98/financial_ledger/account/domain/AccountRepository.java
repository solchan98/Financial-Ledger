package com.solchan98.financial_ledger.account.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @EntityGraph(attributePaths = {"ledgerList"})
    @Query("SELECT DISTINCT a FROM Account a")
    List<Account> findAll();

    boolean existsByEmail(String email);
    Optional<Account> findByEmail(String email);
}
