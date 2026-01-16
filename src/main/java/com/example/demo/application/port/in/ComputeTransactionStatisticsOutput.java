package com.example.demo.application.port.in;

import com.example.demo.domain.MoneySummaryStatistics;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ComputeTransactionStatisticsOutput {
  private final MoneySummaryStatistics summary;
}
