package com.example.demo.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ComputeTransactionStatisticsQuery {

  private final Long lastSeconds;

  public static ComputeTransactionStatisticsQuery of(Long lastSeconds) {
    return new ComputeTransactionStatisticsQuery(lastSeconds);
  }
}
