package com.example.demo.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class TransactionQuery {

  private final LocalDateTime from;
  private final LocalDateTime to;

  public static TransactionQuery of(LocalDateTime from, LocalDateTime to) {
    return new TransactionQuery(from, to);
  }
}
