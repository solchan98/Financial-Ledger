package com.solchan98.financial_ledger.config.content;

public class LedgerContent {

    private LedgerContent() {}
    public static final String CREATE_LEDGER_OK = "가계부 내역을 성공적으로 생성하였습니다.";
    public static final String LEDGER_DATA_BAD_REQUEST = "금액과 메모 내용을 확인하세요.";
    public static final String LEDGER_BAD_REQUEST = "가계부 내역의 ID를 확인하세요.";
    public static final String DELETE_LEDGER_OK = "가계부 내역을 성공적으로 삭제하였습니다.";
    public static final String RESTORE_LEDGER_OK = "가계부 내역을 성공적으로 복구하였습니다.";
    public static final String UPDATE_LEDGER_OK = "가계부 내역을 성공적으로 수정하였습니다.";
    public static final String GET_LEDGER_OK = "가계부 단일 세부내역 조회를 성공하였습니다.";
    public static final String GET_LEDGER_LIST_OK = "가계부 전체 내역 조회를 성공하였습니다.";
    public static final String GET_LEDGER_LIST_BY_DATE_OK = "가계부 전체 내역 년, 월 기준 조회를 성공하였습니다.";
}
