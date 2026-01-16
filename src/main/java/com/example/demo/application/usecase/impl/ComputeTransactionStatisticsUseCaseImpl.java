package com.example.demo.application.usecase.impl;

import com.example.demo.application.port.in.ComputeTransactionStatisticsOutput;
import com.example.demo.application.port.in.ComputeTransactionStatisticsQuery;
import com.example.demo.application.port.in.TransactionQuery;
import com.example.demo.application.port.out.TimeRangeConfig;
import com.example.demo.application.port.out.TransactionRepository;
import com.example.demo.application.usecase.ComputeTransactionStatisticsUseCase;
import com.example.demo.domain.model.MoneySummaryStatistics;
import com.example.demo.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.function.Consumer;
import static com.example.demo.application.helper.LocalDateTimeHelper.truncateSeconds;
import static java.util.Objects.requireNonNullElse;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComputeTransactionStatisticsUseCaseImpl implements ComputeTransactionStatisticsUseCase {

  private final TimeRangeConfig timeRangeConfig;
  private final TransactionRepository transactionRepository;

  @Override
  public ComputeTransactionStatisticsOutput execute(ComputeTransactionStatisticsQuery query) {
    final var defaultTimeRangeInSeconds = timeRangeConfig.getDefaultTimeRangeInSeconds();
    final var timeRangeInSeconds = requireNonNullElse(query.getTimeRangeInSeconds(), defaultTimeRangeInSeconds);
    final var now = LocalDateTime.now();
    final var from = truncateSeconds(now.minusSeconds(timeRangeInSeconds));
    final var to = truncateSeconds(now);
    final var range = TransactionQuery.of(from, to);
    final var summary = MoneySummaryStatistics.empty();
    final var transactions = transactionRepository.findAll(range);
    transactions.parallelStream().forEach(applyingTo(summary));
    return ComputeTransactionStatisticsOutput.of(summary);
  }

  private Consumer<Transaction> applyingTo(MoneySummaryStatistics summary) {
    return it -> summary.accept(it.getAmount());
  }
}
