package com.example.demo.application.port.in;

import com.example.demo.domain.model.Money;
import java.time.LocalDateTime;
import java.util.Objects;

public record RegisterTransactionCommand(Money amount, LocalDateTime occurredAt) {

  public RegisterTransactionCommand {
    Objects.requireNonNull(amount);
  }

  public static RegisterTransactionCommand of(Money amount, LocalDateTime occurredAt) {
    return new RegisterTransactionCommand(amount, occurredAt);
  }

  public static RegisterTransactionCommand now(Money amount) {
    return of(amount, LocalDateTime.now());
  }

}
