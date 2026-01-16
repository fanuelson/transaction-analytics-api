package com.example.demo.application.port.in;

import com.example.demo.domain.model.Money;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class RegisterTransactionCommand {

  private final Money amount;
  private final LocalDateTime occurredAt;

  public static RegisterTransactionCommand of(Money amount, LocalDateTime occurredAt) {
    return new RegisterTransactionCommand(amount, occurredAt);
  }

  public static RegisterTransactionCommand now(Money amount) {
    return of(amount, LocalDateTime.now());
  }

}
