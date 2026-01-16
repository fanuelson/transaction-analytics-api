package com.example.demo.infra.http.controller.resources.response;

import com.example.demo.domain.model.Money;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RegisterTransactionResponse {
  private final BigDecimal valor;
  private final LocalDateTime dataHora;

  public static RegisterTransactionResponse from(Money amount, LocalDateTime occurredAt) {
    return new RegisterTransactionResponse(amount.getAmount(), occurredAt);
  }
}
