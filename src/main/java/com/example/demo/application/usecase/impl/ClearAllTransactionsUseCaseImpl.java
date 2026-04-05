package com.example.demo.application.usecase.impl;

import com.example.demo.application.event.TransactionEventPublisher;
import com.example.demo.application.event.TransactionsDeletedEvent;
import com.example.demo.application.port.in.ClearAllTransactionsOutput;
import com.example.demo.application.usecase.ClearAllTransactionsUseCase;
import com.example.demo.domain.money.Money;
import com.example.demo.domain.transaction.Transaction;
import com.example.demo.domain.transaction.repository.TransactionRepository;
import com.example.demo.domain.transaction.repository.TransactionStatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClearAllTransactionsUseCaseImpl implements ClearAllTransactionsUseCase {

  private final TransactionRepository transactionRepository;
  private final TransactionStatisticsRepository transactionStatisticsRepository;
  private final TransactionEventPublisher eventPublisher;

  @Override
  public ClearAllTransactionsOutput execute() {
    transactionStatisticsRepository.deleteAll();
    final var totalRemoved = transactionRepository.deleteAll();
    eventPublisher.publish(TransactionsDeletedEvent.of(Transaction.of(Money.ofCents(999), LocalDateTime.now())));
    return ClearAllTransactionsOutput.of(totalRemoved);
  }
}
