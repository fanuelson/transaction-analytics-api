package com.example.demo.infra.http.controller.resources.response;

import com.example.demo.domain.model.Money;
import com.example.demo.domain.model.MoneySummaryStatistics;
import java.math.BigDecimal;

public record TransactionSummaryResponse(
  long count,
  BigDecimal sum,
  BigDecimal avg,
  BigDecimal min,
  BigDecimal max
) {

  public static TransactionSummaryResponse from(MoneySummaryStatistics summary) {
    final var isEmpty = summary.isEmpty();
    final var min = isEmpty ? Money.zero() : summary.getMin();
    final var max = isEmpty ? Money.zero() : summary.getMax();
    return new TransactionSummaryResponse(
      summary.getCount(),
      summary.getSum().getAmount(),
      summary.getAvg().getAmount(),
      min.getAmount(),
      max.getAmount()
    );
  }

}
