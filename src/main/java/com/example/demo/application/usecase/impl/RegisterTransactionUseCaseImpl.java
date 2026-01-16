package com.example.demo.application.usecase.impl;

import com.example.demo.application.port.in.RegisterTransactionCommand;
import com.example.demo.application.port.in.RegisterTransactionOutput;
import com.example.demo.application.port.out.TransactionRepository;
import com.example.demo.application.usecase.RegisterTransactionUseCase;
import com.example.demo.domain.exception.BusinessException;
import com.example.demo.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterTransactionUseCaseImpl implements RegisterTransactionUseCase {

  private final TransactionRepository transactionRepository;

  public RegisterTransactionOutput execute(RegisterTransactionCommand command) {
    validate(command);
    final var amount = command.amount();
    final var occurredAt = command.occurredAt();
    final var truncatedOccurredAt = occurredAt.truncatedTo(ChronoUnit.SECONDS);
    final var transaction = Transaction.of(amount, truncatedOccurredAt);
    final var createdTransaction = transactionRepository.save(transaction);
    return RegisterTransactionOutput.of(createdTransaction);
  }

  private void validate(RegisterTransactionCommand command) {
    final var amount = command.amount();
    if (amount.isNegative()) {
      throw BusinessException.of("Valor deve ser maior ou igual a 0");
    }

    if (command.occurredAt().isAfter(LocalDateTime.now())) {
      throw BusinessException.of("Data da transação deve ser anterior a data atual");
    }
  }
}
