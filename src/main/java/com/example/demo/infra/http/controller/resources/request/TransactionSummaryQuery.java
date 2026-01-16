package com.example.demo.infra.http.controller.resources.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class TransactionSummaryQuery {

  @Min(30)
  private Long timeRangeInSeconds;

}
