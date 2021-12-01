package com.solchan98.financial_ledger.config.exception;

import com.solchan98.financial_ledger.config.Status;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorMessage {
    private Status status;
    private String msg;
}
