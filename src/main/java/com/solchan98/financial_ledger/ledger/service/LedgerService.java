package com.solchan98.financial_ledger.ledger.service;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.LedgerContent;
import com.solchan98.financial_ledger.config.exception.Message;
import com.solchan98.financial_ledger.config.exception.ledger.BadRequestLedgerException;
import com.solchan98.financial_ledger.ledger.domain.Ledger;
import com.solchan98.financial_ledger.ledger.domain.LedgerRepository;
import com.solchan98.financial_ledger.ledger.domain.dto.LedgerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    @Transactional
    public LedgerDto.Response createLedger(LedgerDto.Request request, Account account) {
        checkLedgerData(request.getContent(), request.getPrice());
        Ledger ledger = makeLedger(account, request.getContent(), request.getPrice());
        Ledger savedLedger = ledgerRepository.save(ledger);
        Message message = Message.builder().msg(LedgerContent.CREATE_LEDGER_OK).status(Status.CREATE_LEDGER_OK).build();
        return LedgerDto.Response.getLedgerResponse(savedLedger, message);
    }

    private Ledger makeLedger(Account account, String content, Long price) {
        return Ledger.builder().account(account).content(content).price(price).build();
    }

    private void checkLedgerData(String content, Long price) {
        if(content.isEmpty() || price < 0L) {
            throw new BadRequestLedgerException();
        }
    }
}
