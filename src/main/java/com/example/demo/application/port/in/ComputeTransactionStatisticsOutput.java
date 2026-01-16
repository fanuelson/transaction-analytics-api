package com.example.demo.application.port.in;

import com.example.demo.domain.model.MoneySummaryStatistics;

public record ComputeTransactionStatisticsOutput(MoneySummaryStatistics summary) {

  public static ComputeTransactionStatisticsOutput of(MoneySummaryStatistics summary) {
    return new ComputeTransactionStatisticsOutput(summary);
  }

}
