package com.example.demo.application.event;

import com.example.demo.domain.transaction.Transaction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionsDeletedEvent {

  private final Transaction transaction;

  public static TransactionsDeletedEvent of(Transaction transaction) {
    return new TransactionsDeletedEvent(transaction);
  }
}
