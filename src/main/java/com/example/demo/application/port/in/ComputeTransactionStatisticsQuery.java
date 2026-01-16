package com.example.demo.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ComputeTransactionStatisticsQuery {

  private final Long timeRangeInSeconds;

  public static ComputeTransactionStatisticsQuery of(Long timeRangeInSeconds) {
    return new ComputeTransactionStatisticsQuery(timeRangeInSeconds);
  }
}
