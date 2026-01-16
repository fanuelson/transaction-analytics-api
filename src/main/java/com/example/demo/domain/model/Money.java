package com.example.demo.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.util.Objects.requireNonNull;

@Getter
@EqualsAndHashCode
public final class Money {

  private final long amountInCents;

  private Money(long amountInCents) {
    this.amountInCents = amountInCents;
  }

  public static Money zero() {
    return new Money(0L);
  }

  public static Money ofCents(long amountInCents) {
    return new Money(amountInCents);
  }

  public static Money ofCents(BigDecimal inCents) {
    return ofCents(inCents.longValue());
  }

  public static Money of(BigDecimal amount) {
    return ofCents(
      amount.setScale(2, RoundingMode.HALF_EVEN)
        .movePointRight(2)
        .longValue()
    );
  }

  public BigDecimal getAmount() {
    return BigDecimal.valueOf(amountInCents)
      .setScale(0, RoundingMode.HALF_EVEN)
      .movePointLeft(2);
  }

  public Money add(Money other) {
    validate(other);
    return ofCents(this.amountInCents + other.amountInCents);
  }

  public Money subtract(Money other) {
    validate(other);
    return new Money(this.amountInCents - other.amountInCents);
  }

  public Money multiply(double factor) {
    return new Money(Math.round(this.amountInCents * factor));
  }

  public Money divide(long divisor) {
    if (divisor <= 0) {
      throw new ArithmeticException("Divisor must be greater than zero");
    }

    return ofCents(this.amountInCents / divisor);
  }

  public Money min(Money other) {
    validate(other);
    final var min = Long.min(this.getAmountInCents(), other.getAmountInCents());
    return ofCents(min);
  }

  public Money max(Money other) {
    validate(other);
    final var max = Long.max(this.getAmountInCents(), other.getAmountInCents());
    return ofCents(max);
  }

  public boolean isNegative() {
    return amountInCents < 0;
  }

  private void validate(Money other) {
    requireNonNull(other);
  }

  @Override
  public String toString() {
    return String.format("Money(%d cents)", amountInCents);
  }
}