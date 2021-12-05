package com.solchan98.financial_ledger.ledger;

import com.solchan98.financial_ledger.account.AccountTemplate;
import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.config.Status;
import com.solchan98.financial_ledger.config.content.LedgerContent;
import com.solchan98.financial_ledger.config.Message;
import com.solchan98.financial_ledger.config.exception.ledger.BadRequestCreateLedgerException;
import com.solchan98.financial_ledger.config.exception.ledger.BadRequestLedgerException;
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

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("가계부 내역 서비스 로직 테스트")
public class LedgerServiceTest {

    @Mock
    private LedgerRepository ledgerRepository;

    @InjectMocks
    private LedgerService ledgerService;

    @Mock
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
                () -> assertEquals(Status.LEDGER_OK, savedLedger.getMessage().getStatus())
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
        given(ledgerRepository.findByIdAndIsDeleteIsFalse(any())).willReturn(Optional.ofNullable(ledger));
        given(account.getId()).willReturn(1L);
        // when
        Message message = ledgerService.deleteLedger(account, ledger.getId());
        // then
        assertAll(
                () -> assertEquals(Status.DELETE_LEDGER_OK, message.getStatus()),
                () -> assertEquals(LedgerContent.DELETE_LEDGER_OK, message.getMsg())
        );
    }

    @Test
    @DisplayName("가계부 내역 삭제 실패 - 잘못된 내역 ID")
    void deleteLedGerFailByInvalidLedgerId() {
        // given
        Account account2 = mock(Account.class);
        Ledger ledger = LedgerTemplate.makeLedger(account2);
        given(ledgerRepository.findByIdAndIsDeleteIsFalse(any())).willReturn(Optional.ofNullable(ledger));
        given(account.getId()).willReturn(1L);
        given(account2.getId()).willReturn(2L);
        // when then
        assertThrows(BadRequestLedgerException.class, () -> ledgerService.deleteLedger(account, ledger.getId()));
    }

    @Test
    @DisplayName("가계부 내역 복구 성공")
    void restoreLedGerSuccess() {
        // given
        Ledger ledger = LedgerTemplate.makeLedger(account);
        given(ledgerRepository.findByIdAndIsDeleteIsTrue(any())).willReturn(Optional.ofNullable(ledger));
        given(account.getId()).willReturn(1L);
        // when
        LedgerDto.Response response = ledgerService.restoreLedger(account, ledger.getId());
        // then
        assertAll(
                () -> assertEquals(Status.RESTORE_LEDGER_OK, response.getMessage().getStatus()),
                () -> assertEquals(LedgerContent.RESTORE_LEDGER_OK, response.getMessage().getMsg())
        );
    }

    @Test
    @DisplayName("가계부 내역 복구 실패 - 잘못된 내역 ID")
    void restoreLedGerFailByInvalidLedgerId() {
        // given
        Account account2 = mock(Account.class);
        Ledger ledger = LedgerTemplate.makeLedger(account2);
        given(ledgerRepository.findByIdAndIsDeleteIsTrue(any())).willReturn(Optional.ofNullable(ledger));
        given(account.getId()).willReturn(1L);
        given(account2.getId()).willReturn(2L);
        // when then
        assertThrows(BadRequestLedgerException.class, () -> ledgerService.restoreLedger(account, ledger.getId()));
    }

    @Test
    @DisplayName("가계부 단일 내역 조회 성공")
    void getLedGerSuccess() {
        // given
        Ledger ledger = LedgerTemplate.makeLedger(account);
        given(ledgerRepository.findByIdAndIsDeleteIsFalse(any())).willReturn(Optional.ofNullable(ledger));
        given(account.getId()).willReturn(1L);
        // when
        LedgerDto.Response response = ledgerService.getLedger(account, ledger.getId());
        // then
        assertAll(
                () -> assertEquals(Status.LEDGER_OK, response.getMessage().getStatus()),
                () -> assertEquals(LedgerContent.GET_LEDGER_OK, response.getMessage().getMsg())
        );
    }

    @Test
    @DisplayName("가계부 전체 내역 조회 성공")
    void getLedGerListSuccess() {
        // given
        List<Ledger> ledgerList = new ArrayList<>();
        ledgerList.add(LedgerTemplate.makeLedger(account));
        ledgerList.add(LedgerTemplate.makeLedger(account));
        given(ledgerRepository.findAllByAccountAndIsDeleteIsFalseOrderByCreateAtDesc(any())).willReturn(ledgerList);
        // when
        LedgerDto.ListResponse ledgerListResponse = ledgerService.getLedgerList(account);
        // then
        assertAll(
                () -> assertEquals(2, ledgerListResponse.getData().size()),
                () -> assertEquals(Status.LEDGER_OK, ledgerListResponse.getMessage().getStatus()),
                () -> assertEquals(LedgerContent.GET_LEDGER_LIST_OK, ledgerListResponse.getMessage().getMsg())
        );
    }

    @Test
    @DisplayName("가계부 년,월 기준 전체 내역 조회 실패 - 날짜 오류")
    void getLedGerListByDateFail() {
        // given
        // when then
        assertThrows(DateTimeException.class, () -> ledgerService.getLedgerListByDate(account, 2021, 0));
    }


}
