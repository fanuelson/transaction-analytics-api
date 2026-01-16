package com.example.demo.application.port.in;

import com.example.demo.domain.Transaction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterTransactionOutput {

  private final Transaction transaction;

  public static RegisterTransactionOutput of(Transaction transaction) {
    return new RegisterTransactionOutput(transaction);
  }

}
