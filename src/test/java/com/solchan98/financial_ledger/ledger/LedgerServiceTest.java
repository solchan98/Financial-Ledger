package com.solchan98.financial_ledger.ledger;

import com.solchan98.financial_ledger.account.AccountTemplate;
import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.config.Status;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
public class LedgerServiceTest {

    @Mock
    private LedgerRepository ledgerRepository;

    @InjectMocks
    private LedgerService ledgerService;

    static Account account = AccountTemplate.makeTestAccount();

    @Test
    @DisplayName("가계부 생성 성공")
    void createLedGerSuccess() {
        // given
        LedgerDto.Request requestLedger = LedgerTemplate.makeRequestLedger();
        Ledger ledger = LedgerTemplate.makeLedger(account);
        given(ledgerRepository.save(any())).willReturn(ledger);
        // when
        LedgerDto.Response savedLedger = ledgerService.createLedger(requestLedger);
        // then
        assertAll(
                () ->assertEquals(requestLedger.getContent(), savedLedger.getContent()),
                () ->assertEquals(requestLedger.getPrice(), savedLedger.getPrice()),
                () -> assertEquals(Status.CREATE_LEDGER_OK, savedLedger.getMessage().getStatus())
        );
    }

    @Test
    @DisplayName("가계부 생성 실패 - 데이터 빈 값으로 요청")
    void createLedGerFailByNullData() {
        // given

        // when

        // then

    }
}
