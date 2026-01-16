package com.example.demo.application.port.in;

import com.example.demo.domain.Money;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UpdateTransactionSummaryCommand {

  private final Money amount;
  private final LocalDateTime occurredAt;

  public static UpdateTransactionSummaryCommand of(Money amount, LocalDateTime occurredAt) {
    return new UpdateTransactionSummaryCommand(amount, occurredAt);
  }

}
