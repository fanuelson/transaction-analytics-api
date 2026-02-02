package com.example.demo.domain.transaction;

import com.example.demo.domain.money.MoneySummaryStatistics;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TransactionStatisticsBucket {

  private final MoneySummaryStatistics summary;

  public static TransactionStatisticsBucket of(MoneySummaryStatistics summary) {
    return new TransactionStatisticsBucket(summary);
  }

}
