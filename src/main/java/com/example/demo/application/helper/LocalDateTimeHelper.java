package com.example.demo.application.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateTimeHelper {

  private static final ZoneId DEFAULT_ZONE = ZoneId.of("UTC");
  private static final int HALF_OF_MINUTE = 30;
  private static final int END_OF_MINUTE = 59;

  public static Instant extractInstant(final LocalDateTime dateTime) {
    return dateTime.atZone(DEFAULT_ZONE).toInstant();
  }

  public static Instant truncateSeconds(final LocalDateTime dateTime) {
    return extractInstant(dateTime).truncatedTo(ChronoUnit.SECONDS);
  }

  public static Instant truncateMinute(final LocalDateTime dateTime) {
    return extractInstant(dateTime).truncatedTo(ChronoUnit.MINUTES);
  }

  public static Instant truncateToStartOfBlock(final LocalDateTime dateTime) {
    return dateTime.getSecond() >= HALF_OF_MINUTE ?
      truncateMinute(dateTime).plusSeconds(HALF_OF_MINUTE) :
      truncateMinute(dateTime);
  }

  public static Instant truncateToEndOfBlock(final LocalDateTime dateTime) {
    return dateTime.getSecond() >= HALF_OF_MINUTE ?
      truncateMinute(dateTime).plusSeconds(END_OF_MINUTE) :
      truncateMinute(dateTime).plusSeconds(HALF_OF_MINUTE);
  }

  public static long toMillis(Instant instant) {
    return instant.toEpochMilli();
  }

  public static LocalDateTime fromMillis(long epochMillis) {
    return LocalDateTime.ofInstant(
      Instant.ofEpochMilli(epochMillis),
      DEFAULT_ZONE
    );
  }

  public static LocalDateTime fromInstant(Instant instant) {
    return fromMillis(instant.toEpochMilli());
  }

}
