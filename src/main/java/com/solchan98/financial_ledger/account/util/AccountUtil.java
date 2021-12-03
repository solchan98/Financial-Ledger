package com.solchan98.financial_ledger.account.util;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.account.domain.UserAccount;
import org.springframework.security.core.context.SecurityContextHolder;

public class AccountUtil {
    private AccountUtil() {}
    public static Account getAccount() {
        UserAccount principal = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getAccount();
    }
}
