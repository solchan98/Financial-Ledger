package com.solchan98.financial_ledger.ledger.controller;

import com.solchan98.financial_ledger.account.util.AccountUtil;
import com.solchan98.financial_ledger.config.exception.Message;
import com.solchan98.financial_ledger.ledger.domain.dto.LedgerDto;
import com.solchan98.financial_ledger.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ledger")
public class LedgerController {

    private final LedgerService ledgerService;

    @PostMapping("/")
    public LedgerDto.Response createLedger(@RequestBody LedgerDto.Request request) {
        return ledgerService.createLedger(request, AccountUtil.getAccount());
    }

    @DeleteMapping("/{ledgerId}")
    public ResponseEntity<Message> deleteLedger(@PathVariable Long ledgerId) {
        Message message = ledgerService.deleteLedger(AccountUtil.getAccount(), ledgerId);
        return ResponseEntity.ok().body(message);
    }

    @PatchMapping("/")
    public LedgerDto.Response updateLedger(@RequestBody LedgerDto.UpdateRequest request) {
        return ledgerService.updateLedger(AccountUtil.getAccount(), request);
    }

    @GetMapping("/{ledgerId}")
    public LedgerDto.Response getLedger(@PathVariable Long ledgerId) {
//        return ledgerService.getLedger(AccountUtil.getAccount(), ledgerId);
        return null;
    }

    @GetMapping("/{ledgerId}")
    public List<LedgerDto.SimpleResponse> getLedgerList() {
//        return ledgerService.getLedgerList(AccountUtil.getAccount());
        return null;
    }

    @GetMapping("")
    public List<LedgerDto.SimpleResponse> getLedgerListByDate(@RequestParam String date) {
//        return ledgerService.getLedgerListByDate(AccountUtil.getAccount(), date);
        return null;
    }
}
