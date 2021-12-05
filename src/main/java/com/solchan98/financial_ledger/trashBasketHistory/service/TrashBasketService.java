package com.solchan98.financial_ledger.trashBasketHistory.service;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.ledger.domain.dto.LedgerDto;
import com.solchan98.financial_ledger.trashBasketHistory.domain.HistoryType;
import com.solchan98.financial_ledger.trashBasketHistory.domain.TrashBasketHistory;
import com.solchan98.financial_ledger.trashBasketHistory.domain.TrashBasketHistoryRepository;
import com.solchan98.financial_ledger.trashBasketHistory.domain.dto.HistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrashBasketService {

    private final TrashBasketHistoryRepository trashBasketHistoryRepository;

    @Transactional(readOnly = true)
    public HistoryDto getLedgerHistoryList(Account account) {
        List<TrashBasketHistory> historyList = trashBasketHistoryRepository.findAllByLedgerAccount(account);
        return makeHistoryResponseDto(historyList);
    }

    @Transactional(readOnly = true)
    public HistoryDto getRestoreHistoryList(Account account) {
        List<TrashBasketHistory> historyList = trashBasketHistoryRepository.findAllByLedgerAccountAndType(account, HistoryType.RECOVERY);
        return makeHistoryResponseDto(historyList);
    }

    @Transactional(readOnly = true)
    public HistoryDto getDeleteHistoryList(Account account) {
        List<TrashBasketHistory> historyList = trashBasketHistoryRepository.findAllByLedgerAccountAndType(account, HistoryType.DELETE);
        return makeHistoryResponseDto(historyList);
    }

    private HistoryDto makeHistoryResponseDto(List<TrashBasketHistory> history) {
        List<HistoryDto.HistoryResponse> historyList = history.stream().map(x ->
                HistoryDto.HistoryResponse.builder()
                        .history(x)
                        .ledger(LedgerDto.SimpleResponse.getLedgerSimpleResponse(x.getLedger()))
                        .build()).collect(Collectors.toList());
        return HistoryDto.getHistoryDto(historyList);
    }

}
