package com.example.demo.domain.transaction.validator;

import com.example.demo.domain.common.validator.ValidationResult;
import com.example.demo.domain.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionValidatorChain {

  private final List<TransactionValidator> validators;

  public ValidationResult validate(Transaction transaction) {
    final var errors = validators.stream()
      .map(it -> it.validate(transaction))
      .filter(ValidationResult::isInvalid)
      .map(ValidationResult::getErrors)
      .flatMap(Collection::stream)
      .toList();

    return ValidationResult.of(errors);
  }
}
