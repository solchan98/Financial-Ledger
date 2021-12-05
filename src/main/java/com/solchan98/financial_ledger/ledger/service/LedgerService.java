package com.solchan98.financial_ledger.ledger.service;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.LedgerContent;
import com.solchan98.financial_ledger.config.Message;
import com.solchan98.financial_ledger.config.exception.ledger.BadRequestCreateLedgerException;
import com.solchan98.financial_ledger.config.exception.ledger.BadRequestLedgerException;
import com.solchan98.financial_ledger.ledger.domain.Ledger;
import com.solchan98.financial_ledger.ledger.domain.LedgerRepository;
import com.solchan98.financial_ledger.ledger.domain.dto.LedgerDto;
import com.solchan98.financial_ledger.trashBasketHistory.domain.HistoryType;
import com.solchan98.financial_ledger.trashBasketHistory.domain.TrashBasketHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    @Transactional(readOnly = true)
    public LedgerDto.ListResponse getLedgerListByDate(Account account, int year, int month) {
        List<LocalDate> dateList = calculateStartAndEndDate(year, month);
        List<Ledger> ledgerList = ledgerRepository
                .findAllByAccountAndIsDeleteIsFalseAndWriteAtBetween(account, dateList.get(0), dateList.get(1));
        return makeLedgerResponseList(ledgerList);
    }

    @Transactional(readOnly = true)
    public LedgerDto.ListResponse getLedgerList(Account account) {
        List<Ledger> ledgerList = ledgerRepository.findAllByAccountAndIsDeleteIsFalseOrderByCreateAtDesc(account);
        return makeLedgerResponseList(ledgerList);
    }

    @Transactional(readOnly = true)
    public LedgerDto.Response getLedger(Account account, Long ledgerId) {
        Ledger ledger = ledgerRepository.findByIdAndIsDeleteIsFalse(ledgerId)
                .orElseThrow(BadRequestLedgerException::new);
        checkLedGerIsMine(account, ledger);
        Message message = Message.builder().msg(LedgerContent.GET_LEDGER_OK).status(Status.LEDGER_OK).build();
        return LedgerDto.Response.getLedgerResponse(ledger, message);
    }

    @Transactional
    public LedgerDto.Response updateLedger(Account account, LedgerDto.UpdateRequest request) {
        Ledger ledger = ledgerRepository.findByIdAndIsDeleteIsFalse(request.getId())
                .orElseThrow(BadRequestLedgerException::new);
        checkLedGerIsMine(account, ledger);
        checkRequestLedgerData(request.getContent(), request.getPrice());
        ledger.updateLedger(request.getContent(), request.getPrice());
        Ledger updatedLedger = ledgerRepository.save(ledger);
        Message message = Message.builder().msg(LedgerContent.UPDATE_LEDGER_OK).status(Status.LEDGER_OK).build();
        return LedgerDto.Response.getLedgerResponse(updatedLedger, message);
    }

    @Transactional
    public LedgerDto.Response restoreLedger(Account account, Long ledgerId) {
        Ledger ledger = ledgerRepository.findByIdAndIsDeleteIsTrue(ledgerId)
                .orElseThrow(BadRequestLedgerException::new);
        checkLedGerIsMine(account, ledger);
        ledger.restoreLedger();
        TrashBasketHistory history = createTrashBasketHistory(ledger, HistoryType.RECOVERY);
        ledger.addTrashBasket(history);
        ledgerRepository.save(ledger);
        Message message = Message.builder().msg(LedgerContent.RESTORE_LEDGER_OK).status(Status.RESTORE_LEDGER_OK).build();
        return LedgerDto.Response.getLedgerResponse(ledger, message);
    }

    @Transactional
    public Message deleteLedger(Account account, Long ledgerId) {
        Ledger ledger = ledgerRepository.findByIdAndIsDeleteIsFalse(ledgerId)
                .orElseThrow(BadRequestLedgerException::new);
        checkLedGerIsMine(account, ledger);
        ledger.deleteLedger();
        TrashBasketHistory history = createTrashBasketHistory(ledger, HistoryType.DELETE);
        ledger.addTrashBasket(history);
        ledgerRepository.save(ledger);
        return Message.builder().msg(LedgerContent.DELETE_LEDGER_OK).status(Status.DELETE_LEDGER_OK).build();
    }

    @Transactional
    public LedgerDto.Response createLedger(LedgerDto.Request request, Account account) {
        checkRequestLedgerData(request.getContent(), request.getPrice());
        Ledger ledger = makeLedger(account, request.getContent(), request.getPrice());
        Ledger savedLedger = ledgerRepository.save(ledger);
        Message message = Message.builder().msg(LedgerContent.CREATE_LEDGER_OK).status(Status.LEDGER_OK).build();
        return LedgerDto.Response.getLedgerResponse(savedLedger, message);
    }

    private TrashBasketHistory createTrashBasketHistory(Ledger ledger, HistoryType historyType) {
        return TrashBasketHistory.builder().ledger(ledger).type(historyType).build();
    }

    private Ledger makeLedger(Account account, String content, Long price) {
        return Ledger.builder().account(account).content(content).price(price).build();
    }

    private void checkRequestLedgerData(String content, Long price) {
        if(content.isEmpty() || price < 0L) {
            throw new BadRequestCreateLedgerException();
        }
    }

    private void checkLedGerIsMine(Account account, Ledger ledger) {
        if(!account.getId().equals(ledger.getAccount().getId())) {
            throw new BadRequestLedgerException();
        }
    }

    private LedgerDto.ListResponse makeLedgerResponseList(List<Ledger> ledgerList) {
        List<LedgerDto.SimpleResponse> ledgerResponseList = ledgerList.stream()
                .map(LedgerDto.SimpleResponse::getLedgerSimpleResponse).collect(Collectors.toList());
        return LedgerDto.ListResponse.getLedgerSimpleResponse(ledgerResponseList);
    }

    private List<LocalDate> calculateStartAndEndDate(int year, int month) {
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate startDate = LocalDate.of(year, month, 1).minusDays(1);
        LocalDate endDate = LocalDate.of(year, month, 1).plusMonths(1);
        dateList.add(startDate); dateList.add(endDate);
        return dateList;
    }
}
