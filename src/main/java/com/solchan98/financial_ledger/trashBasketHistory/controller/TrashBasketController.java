package com.solchan98.financial_ledger.trashBasketHistory.controller;

import com.solchan98.financial_ledger.account.util.AccountUtil;
import com.solchan98.financial_ledger.trashBasketHistory.domain.dto.HistoryDto;
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
    public HistoryDto getRestoreLedgerList() {
        return trashBasketService.getRestoreHistoryList(AccountUtil.getAccount());
    }

    @GetMapping("/delete-all/")
    public HistoryDto getDeleteLedgerList() {
        return trashBasketService.getDeleteHistoryList(AccountUtil.getAccount());
    }

    @GetMapping("")
    public HistoryDto getTrashLedgerList() {
        return trashBasketService.getLedgerHistoryList(AccountUtil.getAccount());
    }

}
