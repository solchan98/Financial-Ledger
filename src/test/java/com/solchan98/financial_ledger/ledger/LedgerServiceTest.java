package com.solchan98.financial_ledger.ledger;

import com.solchan98.financial_ledger.account.AccountTemplate;
import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.LedgerContent;
import com.solchan98.financial_ledger.config.exception.Message;
import com.solchan98.financial_ledger.config.exception.ledger.BadRequestCreateLedgerException;
import com.solchan98.financial_ledger.ledger.domain.Ledger;
import com.solchan98.financial_ledger.ledger.domain.LedgerRepository;
import com.solchan98.financial_ledger.ledger.domain.dto.LedgerDto;
import com.solchan98.financial_ledger.ledger.service.LedgerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
public class LedgerServiceTest {

    @Mock
    private LedgerRepository ledgerRepository;

    @InjectMocks
    private LedgerService ledgerService;

    static Account account = AccountTemplate.makeTestAccount();

    @Test
    @DisplayName("가계부 내역 생성 성공")
    void createLedGerSuccess() {
        // given
        LedgerDto.Request requestLedger = LedgerTemplate.makeRequestLedger();
        Ledger ledger = LedgerTemplate.makeLedger(account);
        given(ledgerRepository.save(any())).willReturn(ledger);
        // when
        LedgerDto.Response savedLedger = ledgerService.createLedger(requestLedger, account);
        // then
        assertAll(
                () ->assertEquals(requestLedger.getContent(), savedLedger.getContent()),
                () ->assertEquals(requestLedger.getPrice(), savedLedger.getPrice()),
                () -> assertEquals(Status.CREATE_LEDGER_OK, savedLedger.getMessage().getStatus())
        );
    }

    @Test
    @DisplayName("가계부 내역 생성 실패 - 메모내용 빈 값")
    void createLedGerFailByNullContent() {
        // given
        LedgerDto.Request requestLedger = LedgerTemplate.makeRequestLedgerEmptyContent();
        // when then
        assertThrows(BadRequestCreateLedgerException.class, () -> ledgerService.createLedger(requestLedger, account));
    }

    @Test
    @DisplayName("가계부 내역 생성 실패 - 금액이 0보다 작은 경우")
    void createLedGerFailByPriceLessThanZero() {
        // given
        LedgerDto.Request requestLedger = LedgerTemplate.makeRequestLedgerEmptyLessThanZero();
        // when then
        assertThrows(BadRequestCreateLedgerException.class, () -> ledgerService.createLedger(requestLedger, account));
    }

    @Test
    @DisplayName("가계부 내역 삭제 성공")
    void deleteLedGerSuccess() {
        // given
        Ledger ledger = LedgerTemplate.makeLedger(account);
        given(ledgerRepository.findById(any())).willReturn(Optional.ofNullable(ledger));
        // when
        Message message = ledgerService.deleteLedger(account, any());
        // then
        assertAll(
                () -> assertEquals(Status.DELETE_LEDGER_OK, message.getStatus()),
                () -> assertEquals(LedgerContent.DELETE_LEDGER_OK, message.getMsg())
        );
    }
}
