package com.example.demo.application.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateTimeHelper {

  public static LocalDateTime truncateSeconds(final LocalDateTime dateTime) {
    return dateTime.truncatedTo(ChronoUnit.SECONDS);
  }

}
