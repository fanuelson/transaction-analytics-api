package com.example.demo.domain.common.validator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationResult {

  private List<ValidationError> errors;

  public static ValidationResult of(final List<ValidationError> errors) {
    return new ValidationResult(errors);
  }
  public static ValidationResult valid() {
    return of(List.of());
  }
  public static ValidationResult invalid(final List<ValidationError> errors) {
    return of(errors);
  }
  public static ValidationResult invalid(final ValidationError error) {
    return invalid(List.of(error));
  }
  public boolean isInvalid() {
    return errors != null && !errors.isEmpty();
  }
  public boolean isValid() {
    return !isInvalid();
  }

}