package com.example.demo.application.helper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeHelper {

  private LocalDateTimeHelper() {
  }

  public static LocalDateTime truncateSeconds(final LocalDateTime dateTime) {
    return dateTime.truncatedTo(ChronoUnit.SECONDS);
  }

}
