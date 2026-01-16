package com.example.demo.application.usecase.impl;

import com.example.demo.application.helper.LocalDateTimeHelper;
import com.example.demo.application.port.in.RegisterTransactionCommand;
import com.example.demo.application.port.in.RegisterTransactionOutput;
import com.example.demo.application.port.in.UpdateTransactionSummaryCommand;
import com.example.demo.application.port.out.TransactionRepository;
import com.example.demo.application.usecase.RegisterTransactionUseCase;
import com.example.demo.application.usecase.UpdateTransactionSummaryUseCase;
import com.example.demo.domain.Money;
import com.example.demo.domain.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import static com.example.demo.application.helper.LocalDateTimeHelper.*;

@Service
@RequiredArgsConstructor
public class RegisterTransactionUseCaseImpl implements RegisterTransactionUseCase {

  private final UpdateTransactionSummaryUseCase updateTransactionSummaryUseCase;
  private final TransactionRepository transactionRepository;

  //TODO: Validar valor >= 0
  //TODO: Validar data passada
  public RegisterTransactionOutput execute(RegisterTransactionCommand command) {
    final var amount = BigDecimal.valueOf(command.getAmount());
    final var occurredAtInstant = truncateSeconds(command.getOccurredAt());
    final var occurredAt = fromInstant(occurredAtInstant);
    final var transaction = Transaction.of(Money.of(amount), occurredAt);
    final var createdTransaction = transactionRepository.save(transaction);
    final var updateSummaryCommand = UpdateTransactionSummaryCommand.of(Money.of(amount), occurredAt);
    final var summaryUpdatedOutput = updateTransactionSummaryUseCase.execute(updateSummaryCommand);
    final var summaryUpdated = summaryUpdatedOutput.getSummary();
    return RegisterTransactionOutput.of(createdTransaction, summaryUpdated);
  }
}
