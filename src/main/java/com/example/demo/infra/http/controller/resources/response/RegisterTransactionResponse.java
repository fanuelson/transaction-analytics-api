package com.example.demo.infra.http.controller.resources.response;

import com.example.demo.domain.model.Money;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RegisterTransactionResponse(BigDecimal valor, LocalDateTime dataHora) {

  public static RegisterTransactionResponse from(Money amount, LocalDateTime occurredAt) {
    return new RegisterTransactionResponse(amount.getAmount(), occurredAt);
  }

}
