package com.example.demo.application.usecase.impl;

import com.example.demo.application.port.in.ClearAllTransactionsOutput;
import com.example.demo.application.port.out.TransactionRepository;
import com.example.demo.application.usecase.ClearAllTransactionsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClearAllTransactionsUseCaseImpl implements ClearAllTransactionsUseCase {

  private final TransactionRepository transactionRepository;

  @Override
  public ClearAllTransactionsOutput execute() {
    final var totalRemoved = transactionRepository.deleteAll();
    log.info("Total transactions removed: {}", totalRemoved);
    return ClearAllTransactionsOutput.of(totalRemoved);
  }
}
