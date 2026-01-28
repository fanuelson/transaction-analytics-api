package com.example.demo.domain.transaction.validator;

import com.example.demo.domain.common.validator.ValidationResult;
import com.example.demo.domain.transaction.Transaction;
import org.springframework.stereotype.Service;

@Service
public class AmountPositiveValidator implements TransactionValidator {
  @Override
  public ValidationResult validate(Transaction context) {
    if (context.getAmount().isNegative()) {
      return ValidationResult.invalid(TransactionValidationError.amountNegative());
    }

    return ValidationResult.valid();
  }
}
