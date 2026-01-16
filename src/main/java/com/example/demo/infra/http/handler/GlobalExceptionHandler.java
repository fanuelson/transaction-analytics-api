package com.example.demo.infra.http.handler;

import com.example.demo.domain.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Object> handleBusinessValidation(BusinessException ex) {
    final var msg = ex.getMessage();
    log.error(msg, ex);
    return ResponseEntity.unprocessableContent().body(msg);
  }
}
