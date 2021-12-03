package com.solchan98.financial_ledger.ledger.service;

import com.solchan98.financial_ledger.ledger.domain.LedgerRepository;
import com.solchan98.financial_ledger.ledger.domain.dto.LedgerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    public LedgerDto.Response createLedger(LedgerDto.Request request) {

        return null;
    }
}
