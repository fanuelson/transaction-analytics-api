package com.example.demo.application.port.in;

import java.time.LocalDateTime;

public record TransactionQuery(LocalDateTime from, LocalDateTime to) {

  public static TransactionQuery of(LocalDateTime from, LocalDateTime to) {
    return new TransactionQuery(from, to);
  }

}
