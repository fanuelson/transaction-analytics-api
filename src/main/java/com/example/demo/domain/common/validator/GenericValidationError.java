package com.example.demo.domain.common.validator;

public class GenericValidationError implements ValidationError {

  private final String code;
  private final String message;

  private GenericValidationError(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public static ValidationError of(String code, String message) {
    return new GenericValidationError(code, message);
  }

  public String code() {
    return code;
  }

  public String message() {
    return message;
  }

}