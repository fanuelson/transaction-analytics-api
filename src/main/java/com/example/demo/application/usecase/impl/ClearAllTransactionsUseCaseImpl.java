package com.example.demo.application.usecase.impl;

import com.example.demo.application.port.in.ClearAllTransactionsOutput;
import com.example.demo.domain.transaction.repository.TransactionRepository;
import com.example.demo.application.usecase.ClearAllTransactionsUseCase;
import com.example.demo.domain.transaction.repository.TransactionStatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClearAllTransactionsUseCaseImpl implements ClearAllTransactionsUseCase {

  private final TransactionRepository transactionRepository;
  private final TransactionStatisticsRepository transactionStatisticsRepository;

  @Override
  public ClearAllTransactionsOutput execute() {
    transactionStatisticsRepository.deleteAll();
    final var totalRemoved = transactionRepository.deleteAll();
    return ClearAllTransactionsOutput.of(totalRemoved);
  }
}
