package com.example.demo.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClearAllTransactionsOutput {
  private final long totalTransactionsRemoved;
  private final long totalSummaryRemoved;

  public static ClearAllTransactionsOutput of(
    long totalTransactionsRemoved,
    long totalSummaryRemoved
  ) {
    return new ClearAllTransactionsOutput(totalTransactionsRemoved, totalSummaryRemoved);
  }
}
