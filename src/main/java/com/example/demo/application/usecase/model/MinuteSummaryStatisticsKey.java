package com.example.demo.application.usecase.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import static com.example.demo.application.helper.LocalDateTimeHelper.*;
import static java.util.function.Predicate.not;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class MinuteSummaryStatisticsKey implements Comparable<MinuteSummaryStatisticsKey> {

  private final long timestamp;
  private final LocalDateTime occurredAt;

  public static MinuteSummaryStatisticsKey from(LocalDateTime dateTime) {
    final var initialBlockInstant = truncateToStartOfBlock(dateTime);
    final var timestampKey = toMillis(initialBlockInstant);
    return new MinuteSummaryStatisticsKey(timestampKey, dateTime);
  }

  public static Predicate<MinuteSummaryStatisticsKey> isBetween(long from, long to) {
    return not(isBefore(from)).and(not(isAfter(to)));
  }

  public static Predicate<MinuteSummaryStatisticsKey> isBefore(long reference) {
    return it -> it.getTimestamp() < reference;
  }

  public static Predicate<MinuteSummaryStatisticsKey> isAfter(long reference) {
    return it -> it.getTimestamp() > reference;
  }

  public static Collector<MinuteSummaryStatisticsKey, ?, Map<Long, List<MinuteSummaryStatisticsKey>>>
  groupingByKey() {
    return Collectors.groupingBy(
      MinuteSummaryStatisticsKey::getTimestamp,
      Collectors.toList()
    );
  }

  @Override
  public int compareTo(MinuteSummaryStatisticsKey other) {
    return Long.compare(this.getTimestamp(), other.getTimestamp());
  }
}
