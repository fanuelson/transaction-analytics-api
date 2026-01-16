package com.example.demo.infra.http.controller.resources.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDateTime;

public record RegisterTransactionRequest(

  @Min(0)
  @NotNull
  double valor,

  @Past
  LocalDateTime dataHora

) {
}
