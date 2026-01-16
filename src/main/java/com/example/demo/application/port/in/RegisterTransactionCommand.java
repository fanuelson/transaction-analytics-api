package com.example.demo.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class RegisterTransactionCommand {

  private final double amount;
  private final LocalDateTime occurredAt;

  public static RegisterTransactionCommand of(double amount, LocalDateTime occurredAt) {
    return new RegisterTransactionCommand(amount, occurredAt);
  }

  public static RegisterTransactionCommand now(double amount) {
    return of(amount, LocalDateTime.now());
  }

}
