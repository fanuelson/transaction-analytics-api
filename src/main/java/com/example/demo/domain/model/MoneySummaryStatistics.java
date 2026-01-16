package com.example.demo.domain.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneySummaryStatistics {

  private final LongAdder count;
  private final AtomicReference<Money> sum;
  private final AtomicReference<Money> min;
  private final AtomicReference<Money> max;

  public static MoneySummaryStatistics empty() {
    return new MoneySummaryStatistics(
      new LongAdder(),
      new AtomicReference<>(Money.zero()),
      new AtomicReference<>(Money.ofCents(Long.MAX_VALUE)),
      new AtomicReference<>(Money.ofCents(Long.MIN_VALUE))
    );
  }

  public void accept(Money other) {
    count.increment();
    sum.updateAndGet(it -> it.add(other));
    min.getAndUpdate(prev -> prev.min(other));
    max.getAndUpdate(prev -> prev.max(other));
  }

  public Money getAvg() {
    long count = getCount();
    return count == 0 ? Money.zero() : getSum().divide(count);
  }

  public long getCount() {
    return count.longValue();
  }

  public Money getSum() {
    return sum.get();
  }

  public Money getMin() {
    return min.get();
  }

  public Money getMax() {
    return max.get();
  }

  public boolean isEmpty() {
    return count.longValue() == 0;
  }

}
