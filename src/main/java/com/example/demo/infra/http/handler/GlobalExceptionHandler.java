package com.example.demo.infra.http.handler;

import com.example.demo.domain.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Object> handleBusinessValidation(BusinessException ex) {
    final var msg = ex.getMessage();
    log.error(msg, ex);
    return ResponseEntity.unprocessableContent().body(msg);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
    final var errors = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(formatMessage())
      .toList();
    return ResponseEntity.badRequest().body(Map.of("errors", errors));
  }

  private Function<FieldError, String> formatMessage() {
    return field -> String.join(" ", field.getField(), field.getDefaultMessage());
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<String> handleInternalServerException(final Exception ex) {
    return ResponseEntity.internalServerError().body("Internal Server Error");
  }

}
