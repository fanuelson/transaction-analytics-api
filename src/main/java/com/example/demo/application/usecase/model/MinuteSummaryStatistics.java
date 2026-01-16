package com.example.demo.application.usecase.model;

import com.example.demo.application.helper.LocalDateTimeHelper;
import com.example.demo.domain.Money;
import com.example.demo.domain.MoneySummaryStatistics;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(of = "key")
public class MinuteSummaryStatistics implements Comparable<MinuteSummaryStatistics> {

  private MinuteSummaryStatisticsKey key;
  private MoneySummaryStatistics summary;

  public static MinuteSummaryStatistics of(LocalDateTime occurredAt, Money value) {
    var summary = MoneySummaryStatistics.empty();
    if (value.isPositive()) {
      summary.accept(value);
    }
    final var key = MinuteSummaryStatisticsKey.from(occurredAt);
    return new MinuteSummaryStatistics(key, summary);
  }

  public MinuteSummaryStatistics accept(MoneySummaryStatistics stat) {
    summary.combine(stat);
    return this;
  }

  public static Collector<MinuteSummaryStatistics, ?, Map<Long, List<MinuteSummaryStatistics>>>
  groupingByKey() {
    return Collectors.groupingBy(
      it -> it.getKey().getTimestamp(),
      Collectors.toList()
    );
  }

  public static UnaryOperator<MinuteSummaryStatistics> accepting(MoneySummaryStatistics otherSummary) {
    return it -> it.accept(otherSummary);
  }

  @Override
  public int compareTo(MinuteSummaryStatistics other) {
    return this.getKey().compareTo(other.getKey());
  }
}
