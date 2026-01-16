package com.example.demo.domain.exception;

public class BusinessException extends RuntimeException {

  public BusinessException(String message) {
    super(message);
  }

  public static BusinessException of(String msg) {
    return new BusinessException(msg);
  }

}
