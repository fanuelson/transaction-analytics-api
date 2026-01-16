package com.example.demo.application.port.in;

import com.example.demo.domain.model.MoneySummaryStatistics;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ComputeTransactionStatisticsOutput {

  private final MoneySummaryStatistics summary;

  public static ComputeTransactionStatisticsOutput of(MoneySummaryStatistics summary) {
    return new ComputeTransactionStatisticsOutput(summary);
  }
}
