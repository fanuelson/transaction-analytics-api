package com.example.demo.application.usecase.impl;

import com.example.demo.application.port.in.RegisterTransactionCommand;
import com.example.demo.application.port.in.RegisterTransactionOutput;
import com.example.demo.application.usecase.RegisterTransactionUseCase;
import com.example.demo.domain.common.exception.BusinessValidationException;
import com.example.demo.domain.transaction.Transaction;
import com.example.demo.domain.transaction.repository.TransactionRepository;
import com.example.demo.domain.transaction.validator.TransactionValidatorChain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterTransactionUseCaseImpl implements RegisterTransactionUseCase {

  private final TransactionRepository repository;
  private final TransactionValidatorChain validator;

  public RegisterTransactionOutput execute(RegisterTransactionCommand command) {
    validate(command);
    final var amount = command.amount();
    final var occurredAt = command.occurredAt();
    final var truncatedOccurredAt = occurredAt.truncatedTo(ChronoUnit.SECONDS);
    final var transaction = Transaction.of(amount, truncatedOccurredAt);
    final var createdTransaction = repository.save(transaction);
    return RegisterTransactionOutput.of(createdTransaction);
  }

  private void validate(RegisterTransactionCommand command) {
    final var result = validator.validate(Transaction.of(command.amount(), command.occurredAt()));
    if (result.isInvalid()) {
      throw BusinessValidationException.from(result);
    }
  }
}
