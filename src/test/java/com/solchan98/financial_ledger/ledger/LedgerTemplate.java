package com.solchan98.financial_ledger.ledger;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.ledger.domain.Ledger;
import com.solchan98.financial_ledger.ledger.domain.dto.LedgerDto;

public class LedgerTemplate {

    public static final String CONTENT = "식재자 마트 영수증 좔좔---";
    public static final Long PRICE = 50000L;
    public static final String EMPTY_CONTENT = "";
    public static final Long LESS_THEN_ZERO_PRICE = -20L;

    public static Ledger makeLedger(Account account) {
        return Ledger.builder().account(account).content(CONTENT).price(PRICE).build();
    }

    public static LedgerDto.Request makeRequestLedger() {
        return LedgerDto.Request.builder().content(CONTENT).price(PRICE).build();
    }

    public static LedgerDto.Request makeRequestLedgerEmptyContent() {
        return LedgerDto.Request.builder().content(EMPTY_CONTENT).price(PRICE).build();
    }
    public static LedgerDto.Request makeRequestLedgerEmptyLessThanZero() {
        return LedgerDto.Request.builder().content(CONTENT).price(LESS_THEN_ZERO_PRICE).build();
    }
}
