package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import static java.util.function.Predicate.*;

@Getter
@AllArgsConstructor
public class Transaction {

  private TransactionId id;
  private Money amount;
  private LocalDateTime occurredAt;

  public static Transaction of(Money amount, LocalDateTime occurredAt) {
    return new Transaction(null, amount, occurredAt);
  }

  public Transaction withId(String id) {
    return new Transaction(TransactionId.create(id), this.getAmount(), this.getOccurredAt());
  }

  public static Predicate<Transaction> isBetween(LocalDateTime from, LocalDateTime to) {
    return not(isBefore(from)).and(not(isAfter(to)));
  }

  public static Predicate<Transaction> isBefore(LocalDateTime reference) {
    return it -> it.getOccurredAt().isBefore(reference);
  }

  public static Predicate<Transaction> isAfter(LocalDateTime reference) {
    return it -> it.getOccurredAt().isAfter(reference);
  }

}
