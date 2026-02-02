package com.example.demo.application.event;

import com.example.demo.domain.transaction.Transaction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionCreatedEvent {

  private final Transaction transaction;

  public static TransactionCreatedEvent of(Transaction transaction) {
    return new TransactionCreatedEvent(transaction);
  }
}
