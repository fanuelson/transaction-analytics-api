package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneySummaryStatistics {

  private long count;
  private Money sum;
  private Money min;
  private Money max;

  public static MoneySummaryStatistics empty() {
    return new MoneySummaryStatistics(
      0L,
      Money.zero(),
      Money.ofCents(Long.MAX_VALUE),
      Money.ofCents(Long.MIN_VALUE)
    );
  }

  public void accept(Money other) {
    ++count;
    sum = sum.add(other);
    min = min.min(other);
    max = max.max(other);
  }

  public MoneySummaryStatistics combine(MoneySummaryStatistics other) {
    count += other.count;
    sum = sum.add(other.getSum());
    min = min.min(other.getMin());
    max = max.max(other.getMax());
    return this;
  }

  public Money getAvg() {
    return isEmpty() ? Money.zero() : sum.divide(count);
  }

  public boolean isEmpty() {
    return count == 0;
  }

}
