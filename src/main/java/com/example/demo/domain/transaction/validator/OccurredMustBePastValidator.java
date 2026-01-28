package com.example.demo.domain.transaction.validator;

import com.example.demo.domain.common.validator.ValidationResult;
import com.example.demo.domain.transaction.Transaction;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class OccurredMustBePastValidator implements TransactionValidator {

  @Override
  public ValidationResult validate(Transaction context) {
    if (context.getOccurredAt().isAfter(LocalDateTime.now())) {
      return ValidationResult.invalid(TransactionValidationError.occurredAtFuture());
    }

    return ValidationResult.valid();
  }
}
