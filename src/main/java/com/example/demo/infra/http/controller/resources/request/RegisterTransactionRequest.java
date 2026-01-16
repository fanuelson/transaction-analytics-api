package com.example.demo.infra.http.controller.resources.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RegisterTransactionRequest {
  private double valor;
  private LocalDateTime dataHora;
}
