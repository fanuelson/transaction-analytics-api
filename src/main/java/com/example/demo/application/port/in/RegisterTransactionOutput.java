package com.example.demo.application.port.in;

import com.example.demo.domain.transaction.Transaction;

public record RegisterTransactionOutput(Transaction transaction) {

  public static RegisterTransactionOutput of(Transaction transaction) {
    return new RegisterTransactionOutput(transaction);
  }

}
