package com.example.demo.infra.http.controller.resources.request;

import lombok.Data;

@Data
public class TransactionSummaryQuery {
  private Long lastSeconds;
}
