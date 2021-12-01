package com.solchan98.financial_ledger.config.exception;

import com.solchan98.financial_ledger.config.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private Status status;
    private String msg;
}
