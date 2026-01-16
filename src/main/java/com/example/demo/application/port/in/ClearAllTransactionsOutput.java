package com.example.demo.application.port.in;

public record ClearAllTransactionsOutput(long totalRemoved) {

  public static ClearAllTransactionsOutput of(long totalRemoved) {
    return new ClearAllTransactionsOutput(totalRemoved);
  }

}
