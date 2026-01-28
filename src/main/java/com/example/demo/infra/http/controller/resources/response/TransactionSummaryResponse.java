package com.example.demo.infra.http.controller.resources.response;

import com.example.demo.domain.money.MoneySummaryStatistics;
import com.example.demo.domain.money.Money;
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
      summary.getSum().amount(),
      summary.getAvg().amount(),
      min.amount(),
      max.amount()
    );
  }

}
