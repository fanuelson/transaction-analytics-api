package com.example.demo.domain.transaction.vo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public record TransactionStatisticsKey(long value) {

  public static TransactionStatisticsKey of(LocalDateTime dateTime) {
    return new TransactionStatisticsKey(
      dateTime
        .truncatedTo(ChronoUnit.SECONDS)
        .atZone(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()
    );
  }

}
