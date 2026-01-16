package com.example.demo.domain.model;


public record TransactionId(String value) {

  public static TransactionId of(String id) {
    return new TransactionId(id);
  }

}
