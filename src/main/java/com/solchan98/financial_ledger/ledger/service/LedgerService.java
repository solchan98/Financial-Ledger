package com.solchan98.financial_ledger.ledger.service;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.LedgerContent;
import com.solchan98.financial_ledger.config.exception.Message;
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

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    public LedgerDto.Response restoreLedger(Account account, Long ledGerId) {
        Ledger ledger = ledgerRepository.findByIdAndIsDeleteIsTrue(ledGerId)
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
    public Message deleteLedger(Account account, Long ledGerId) {
        Ledger ledger = ledgerRepository.findByIdAndIsDeleteIsFalse(ledGerId)
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
        checkCreateLedgerData(request.getContent(), request.getPrice());
        Ledger ledger = makeLedger(account, request.getContent(), request.getPrice());
        Ledger savedLedger = ledgerRepository.save(ledger);
        Message message = Message.builder().msg(LedgerContent.CREATE_LEDGER_OK).status(Status.CREATE_LEDGER_OK).build();
        return LedgerDto.Response.getLedgerResponse(savedLedger, message);
    }

    private TrashBasketHistory createTrashBasketHistory(Ledger ledger, HistoryType historyType) {
        return TrashBasketHistory.builder().ledger(ledger).type(historyType).build();
    }

    private Ledger makeLedger(Account account, String content, Long price) {
        return Ledger.builder().account(account).content(content).price(price).build();
    }

    private void checkCreateLedgerData(String content, Long price) {
        if(content.isEmpty() || price < 0L) {
            throw new BadRequestCreateLedgerException();
        }
    }

    private void checkLedGerIsMine(Account account, Ledger ledger) {
        if(!account.getId().equals(ledger.getAccount().getId())) {
            throw new BadRequestLedgerException();
        }
    }
}
