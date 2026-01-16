package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import static java.util.function.Predicate.not;

@Getter
@ToString
@AllArgsConstructor
public class Transaction {

  private TransactionId id;
  private Money amount;
  private LocalDateTime occurredAt;

  public static Transaction of(Money amount, LocalDateTime occurredAt) {
    return new Transaction(null, amount, occurredAt);
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

  public Transaction withId(TransactionId id) {
    return new Transaction(id, this.getAmount(), this.getOccurredAt());
  }

}
