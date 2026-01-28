package com.example.demo.domain.common.exception;

import com.example.demo.domain.common.validator.ValidationError;
import com.example.demo.domain.common.validator.ValidationResult;
import lombok.Getter;
import java.util.stream.Collectors;

@Getter
public class BusinessValidationException extends RuntimeException {

  private final ValidationResult validationResult;

  public BusinessValidationException(ValidationResult result) {
    super(result.getErrors().stream().map(ValidationError::message).collect(Collectors.joining(", ")));
    this.validationResult = result;
  }

  public static BusinessValidationException from(ValidationResult result) {
    return new BusinessValidationException(result);
  }
}
