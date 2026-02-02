package com.example.demo.application.event;

import com.example.demo.domain.money.Money;
import com.example.demo.domain.money.MoneySummaryStatistics;
import com.example.demo.domain.transaction.TransactionStatisticsBucket;
import com.example.demo.domain.transaction.repository.TransactionStatisticsRepository;
import com.example.demo.domain.transaction.vo.TransactionStatisticsKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionStatisticsManager {

  private final TransactionStatisticsRepository transactionStatisticsRepository;

  @Async
  @EventListener
  public void onTransactionCreated(TransactionCreatedEvent event) {
    final var occurredAt = event.getTransaction().getOccurredAt();
    final var key = TransactionStatisticsKey.of(occurredAt);
    transactionStatisticsRepository.save(
      key, (existing) -> handle(existing, event.getTransaction().getAmount())
    );
  }

  private TransactionStatisticsBucket handle(TransactionStatisticsBucket existing, Money amount) {
    final var summary = ofNullable(existing)
      .map(TransactionStatisticsBucket::getSummary)
      .orElse(MoneySummaryStatistics.empty());
    summary.accept(amount);
    return TransactionStatisticsBucket.of(summary);
  }
}
