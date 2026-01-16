package com.example.demo.application.port.in;

public record ComputeTransactionStatisticsQuery(Long timeRangeInSeconds) {

  public static ComputeTransactionStatisticsQuery of(Long timeRangeInSeconds) {
    return new ComputeTransactionStatisticsQuery(timeRangeInSeconds);
  }

}
