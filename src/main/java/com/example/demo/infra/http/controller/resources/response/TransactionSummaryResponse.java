package com.example.demo.infra.http.controller.resources.response;

import com.example.demo.domain.model.Money;
import com.example.demo.domain.model.MoneySummaryStatistics;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class TransactionSummaryResponse {

  private final long count;
  private final BigDecimal sum;
  private final BigDecimal avg;
  private final BigDecimal min;
  private final BigDecimal max;

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
