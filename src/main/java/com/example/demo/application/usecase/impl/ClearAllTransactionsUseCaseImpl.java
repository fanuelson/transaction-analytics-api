package com.example.demo.application.usecase.impl;

import com.example.demo.application.port.in.ClearAllTransactionsOutput;
import com.example.demo.application.port.out.MinuteSummaryStatRepository;
import com.example.demo.application.port.out.TransactionRepository;
import com.example.demo.application.usecase.ClearAllTransactionsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClearAllTransactionsUseCaseImpl implements ClearAllTransactionsUseCase {

  private final TransactionRepository transactionRepository;
  private final MinuteSummaryStatRepository minuteSummaryStatRepository;

  @Override
  public ClearAllTransactionsOutput execute() {
    final var totalTransactionsRemoved = transactionRepository.deleteAll();
    final var totalSummaryRemoved = minuteSummaryStatRepository.deleteAll();
    return ClearAllTransactionsOutput.of(totalTransactionsRemoved, totalSummaryRemoved);
  }
}
