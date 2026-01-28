package com.example.demo.domain.common.validator;

public interface Validator<C> {

  ValidationResult validate(C context);
}
