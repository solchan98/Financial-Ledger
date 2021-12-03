package com.solchan98.financial_ledger.trashBasketHistory.controller;

import com.solchan98.financial_ledger.account.util.AccountUtil;
import com.solchan98.financial_ledger.trashBasketHistory.domain.dto.HistoryResponse;
import com.solchan98.financial_ledger.trashBasketHistory.service.TrashBasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trash-basket")
public class TrashBasketController {

    private final TrashBasketService trashBasketService;

    @GetMapping("/restore-all/")
    public List<HistoryResponse> getRestoreLedgerList() {
        return trashBasketService.getRestoreHistoryList(AccountUtil.getAccount());
    }

    @GetMapping("/delete-all/")
    public List<HistoryResponse> getDeleteLedgerList() {
        return trashBasketService.getDeleteHistoryList(AccountUtil.getAccount());
    }

    @GetMapping("")
    public List<HistoryResponse> getTrashLedgerList() {
        return trashBasketService.getLedgerHistoryList(AccountUtil.getAccount());
    }

}
