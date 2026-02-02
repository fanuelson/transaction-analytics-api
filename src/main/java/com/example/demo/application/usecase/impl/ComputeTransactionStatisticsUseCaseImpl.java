package com.example.demo.application.usecase.impl;

import com.example.demo.application.port.in.ComputeTransactionStatisticsOutput;
import com.example.demo.application.port.in.ComputeTransactionStatisticsQuery;
import com.example.demo.application.port.out.TimeRangeConfig;
import com.example.demo.application.usecase.ComputeTransactionStatisticsUseCase;
import com.example.demo.domain.money.MoneySummaryStatistics;
import com.example.demo.domain.transaction.TransactionStatisticsBucket;
import com.example.demo.domain.transaction.repository.TransactionStatisticsRepository;
import com.example.demo.domain.transaction.vo.TransactionStatisticsKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import static java.util.Objects.requireNonNullElse;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComputeTransactionStatisticsUseCaseImpl implements ComputeTransactionStatisticsUseCase {

  private final TimeRangeConfig timeRangeConfig;
  private final TransactionStatisticsRepository transactionStatisticsRepository;

  @Override
  public ComputeTransactionStatisticsOutput execute(ComputeTransactionStatisticsQuery query) {
    final var defaultTimeRangeInSeconds = timeRangeConfig.getDefaultTimeRangeInSeconds();
    final var timeRangeInSeconds = requireNonNullElse(query.timeRangeInSeconds(), defaultTimeRangeInSeconds);
    final var now = LocalDateTime.now();
    final var key = TransactionStatisticsKey.of(now.minusSeconds(timeRangeInSeconds));
    final var summary = MoneySummaryStatistics.empty();
    final var transactions = transactionStatisticsRepository.findAllAfter(key);
    transactions.parallelStream()
      .map(TransactionStatisticsBucket::getSummary)
      .forEach(summary::combine);
    return ComputeTransactionStatisticsOutput.of(summary);
  }

}
