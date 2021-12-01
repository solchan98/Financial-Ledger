package com.solchan98.financial_ledger.trashBasketHistory.domain;

public enum HistoryType {
    DELETE(0, "DELETE"),
    RECOVERY(1, "RECOVERY");

    int index;
    String type;

    HistoryType(int index, String type) {}
}
