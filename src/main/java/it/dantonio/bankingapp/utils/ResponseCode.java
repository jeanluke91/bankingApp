package it.dantonio.bankingapp.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

  OK("0", "OK"),

  NO_BALANCE_FOUND("-100", "NoAccountBalanceFound"),

  MONEY_TRANSFER_ERROR("API000",
      "Errore tecnico. La condizione BP049 non e' prevista per il conto id "),
  UNEXPECTED_ERROR("-200", "UnexpectedError"),

  NO_TRANSACTION_FOUND("-300", "NoTransactionFound");

  private final String code;
  private final String msg;

}
