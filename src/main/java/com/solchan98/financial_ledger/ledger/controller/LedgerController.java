package com.solchan98.financial_ledger.ledger.controller;

import com.solchan98.financial_ledger.account.util.AccountUtil;
import com.solchan98.financial_ledger.config.exception.Message;
import com.solchan98.financial_ledger.ledger.domain.dto.LedgerDto;
import com.solchan98.financial_ledger.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ledger")
public class LedgerController {

    private final LedgerService ledgerService;

    @PostMapping("")
    public LedgerDto.Response createLedger(@RequestBody LedgerDto.Request request) {
        return ledgerService.createLedger(request, AccountUtil.getAccount());
    }

    @DeleteMapping("/{ledgerId}")
    public ResponseEntity<Message> deleteLedger(@PathVariable Long ledgerId) {
        Message message = ledgerService.deleteLedger(AccountUtil.getAccount(), ledgerId);
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/restore/{ledgerId}")
    public LedgerDto.Response restoreLedger(@PathVariable Long ledgerId) {
        return ledgerService.restoreLedger(AccountUtil.getAccount(), ledgerId);
    }
}
