package com.example.demo.domain;

import java.util.UUID;
import static java.util.Objects.*;

public record TransactionId(String value) {

  public TransactionId {
    value = requireNonNullElse(value, generateId());
  }

  public static TransactionId create(String id) {
    return new TransactionId(id);
  }

  public static TransactionId create() {
    return create(null);
  }

  private static String generateId() {
    return UUID.randomUUID().toString();
  }

}
