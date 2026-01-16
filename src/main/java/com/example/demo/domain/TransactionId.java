package com.example.demo.domain;


public record TransactionId(String value) {

  public static TransactionId of(String id) {
    return new TransactionId(id);
  }

}
