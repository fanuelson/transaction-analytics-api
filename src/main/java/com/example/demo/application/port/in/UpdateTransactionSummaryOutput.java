package com.example.demo.application.port.in;

import com.example.demo.application.usecase.model.MinuteSummaryStatistics;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateTransactionSummaryOutput {

  private final MinuteSummaryStatistics summary;

  public static UpdateTransactionSummaryOutput of(MinuteSummaryStatistics summary) {
    return new UpdateTransactionSummaryOutput(summary);
  }
}
