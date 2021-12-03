package com.solchan98.financial_ledger.ledger.controller;

import com.solchan98.financial_ledger.account.util.AccountUtil;
import com.solchan98.financial_ledger.ledger.domain.dto.LedgerDto;
import com.solchan98.financial_ledger.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ledger")
public class LedgerController {

    private final LedgerService ledgerService;

    @PostMapping("")
    public LedgerDto.Response createLedger(@RequestBody LedgerDto.Request request) {
        return ledgerService.createLedger(request, AccountUtil.getAccount());
    }
}
