package com.solchan98.financial_ledger;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.account.domain.AccountRepository;
import com.solchan98.financial_ledger.ledger.domain.Ledger;
import com.solchan98.financial_ledger.ledger.domain.LedgerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QueryTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LedgerRepository ledgerRepository;

    @Test
    @DisplayName("일단 조회")
    @Transactional
    void getDate() {
        List<Account> all = accountRepository.findAll();
        System.out.println("--------------------------");
        for(Account account: all) {
            System.out.println(account.getLedgerList().size());
        }
        System.out.println("--------------------------");
    }
}
