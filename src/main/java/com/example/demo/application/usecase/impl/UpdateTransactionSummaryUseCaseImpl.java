package com.example.demo.application.usecase.impl;

import com.example.demo.application.port.in.UpdateTransactionSummaryCommand;
import com.example.demo.application.port.in.UpdateTransactionSummaryOutput;
import com.example.demo.application.port.out.MinuteSummaryStatRepository;
import com.example.demo.application.usecase.UpdateTransactionSummaryUseCase;
import com.example.demo.application.usecase.model.MinuteSummaryStatistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateTransactionSummaryUseCaseImpl implements UpdateTransactionSummaryUseCase {

  private final MinuteSummaryStatRepository minuteSummaryStatRepository;

  @Override
  public UpdateTransactionSummaryOutput execute(UpdateTransactionSummaryCommand command) {
    final var occurredAt = command.getOccurredAt();
    final var amount = command.getAmount();
    final var summary = MinuteSummaryStatistics.of(occurredAt, amount);
    final var savedSummary = minuteSummaryStatRepository.saveOrUpdate(summary);
    return new UpdateTransactionSummaryOutput(savedSummary);
  }

}
