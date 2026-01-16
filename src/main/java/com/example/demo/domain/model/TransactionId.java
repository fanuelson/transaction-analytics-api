package com.example.demo.domain.model;

import java.util.Objects;

public record TransactionId(String value) {

  public TransactionId {
    Objects.requireNonNull(value);
  }

  public static TransactionId of(String id) {
    return new TransactionId(id);
  }

}
