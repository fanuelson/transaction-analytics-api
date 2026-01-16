package com.example.demo.application.usecase.impl;

import com.example.demo.application.port.in.ComputeTransactionStatisticsOutput;
import com.example.demo.application.port.in.ComputeTransactionStatisticsQuery;
import com.example.demo.application.port.out.MinuteSummaryStatRepository;
import com.example.demo.application.usecase.ComputeTransactionStatisticsUseCase;
import com.example.demo.application.usecase.model.MinuteSummaryStatistics;
import com.example.demo.application.usecase.model.MinuteSummaryStatisticsKey;
import com.example.demo.domain.MoneySummaryStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import static com.example.demo.application.helper.LocalDateTimeHelper.*;
import static java.util.Objects.*;

@Service
@RequiredArgsConstructor
public class ComputeTransactionSummaryUseCaseImpl implements ComputeTransactionStatisticsUseCase {

  private static final long DEFAULT_LAST_SECONDS = 60L;
  private final MinuteSummaryStatRepository minuteSummaryStatRepository;

  @Override
  public ComputeTransactionStatisticsOutput execute(ComputeTransactionStatisticsQuery query) {
    final var now = LocalDateTime.now();
    final var to = toMillis(truncateToEndOfBlock(now));
    final var lastSeconds = requireNonNullElse(query.getLastSeconds(), DEFAULT_LAST_SECONDS);
    final var from = toMillis(truncateToStartOfBlock(now.minusSeconds(lastSeconds)));
    final var keys = minuteSummaryStatRepository.findAllKeys().stream()
      .filter(MinuteSummaryStatisticsKey.isBetween(from, to))
      .toList();
    final var stats = keys.stream()
      .map(minuteSummaryStatRepository::findByKey)
      .flatMap(Optional::stream)
      .map(MinuteSummaryStatistics::getSummary)
      .reduce(MoneySummaryStatistics.empty(), MoneySummaryStatistics::combine);
    return new ComputeTransactionStatisticsOutput(stats);
  }
}
