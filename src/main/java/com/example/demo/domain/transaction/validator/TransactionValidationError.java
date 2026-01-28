package com.example.demo.domain.transaction.validator;

import com.example.demo.domain.common.validator.ValidationError;

public class TransactionValidationError implements ValidationError {

  private final String code;
  private final String message;

  private TransactionValidationError(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public static TransactionValidationError of(String code, String message) {
    return new TransactionValidationError(code, message);
  }

  public static TransactionValidationError amountNegative() {
    return of("AMOUNT_NEGATIVE", "Valor deve ser maior ou igual a 0");
  }

  public static TransactionValidationError occurredAtFuture() {
    return of("OCCURRED_AT_FUTURE", "Data da transação deve ser anterior a data atual");
  }

  @Override
  public String code() {
    return code;
  }

  @Override
  public String message() {
    return message;
  }
}
