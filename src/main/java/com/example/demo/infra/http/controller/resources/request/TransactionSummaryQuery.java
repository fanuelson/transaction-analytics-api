package com.example.demo.infra.http.controller.resources.request;

import jakarta.validation.constraints.Min;

public record TransactionSummaryQuery(

  @Min(30)
  Long timeRangeInSeconds

) {
}
