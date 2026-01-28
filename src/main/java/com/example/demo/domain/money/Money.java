package com.example.demo.domain.money;

import java.math.BigDecimal;

public interface Money {

  static Money zero() {
    return LongMoney.zero();
  }
  static Money ofCents(long amountInCents) {
    return LongMoney.ofCents(amountInCents);
  }
  static Money of(BigDecimal amount) {
    return LongMoney.of(amount);
  }
  BigDecimal amount();
  long amountInCents();
  Money add(Money money);
  Money subtract(Money other);
  Money multiply(double factor);
  Money divide(long divisor);
  Money min(Money other);
  Money max(Money other);
  boolean isNegative();
}
