package com.example.demo.domain.transaction;


import com.example.demo.domain.money.Money;
import com.example.demo.domain.transaction.vo.TransactionId;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import static java.util.function.Predicate.not;


public interface Transaction {

  static Transaction of(Money amount, LocalDateTime occurredAt) {
    return new DefaultTransaction(null, amount, occurredAt);
  }
  static Predicate<Transaction> isBetween(LocalDateTime from, LocalDateTime to) {
    return not(isBefore(from)).and(not(isAfter(to)));
  }
  static Predicate<Transaction> isBefore(LocalDateTime reference) {
    return it -> it.getOccurredAt().isBefore(reference);
  }
  static Predicate<Transaction> isAfter(LocalDateTime reference) {
    return it -> it.getOccurredAt().isAfter(reference);
  }
  TransactionId getId();
  Transaction withId(TransactionId id);
  Money getAmount();
  LocalDateTime getOccurredAt();

}
