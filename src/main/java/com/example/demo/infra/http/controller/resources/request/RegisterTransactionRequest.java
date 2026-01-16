package com.example.demo.infra.http.controller.resources.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RegisterTransactionRequest {

  @Min(0)
  @NotNull
  private double valor;

  @Past
  @NotNull
  private LocalDateTime dataHora;
}
