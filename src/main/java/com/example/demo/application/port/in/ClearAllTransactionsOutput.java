package com.example.demo.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClearAllTransactionsOutput {

  private final long totalRemoved;

  public static ClearAllTransactionsOutput of(long totalRemoved) {
    return new ClearAllTransactionsOutput(totalRemoved);
  }
}
