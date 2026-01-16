package com.example.demo.infra.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.function.Supplier;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExecutionTimeUtils {

  public static void duration(String name, Runnable runnable) {
    logExecuting(name);
    long start = System.nanoTime();
    try {
      runnable.run();
    } finally {
      logDuration(name, start);
    }
  }

  public static <T> T duration(String name, Supplier<T> supplier) {
    logExecuting(name);
    long start = System.nanoTime();
    try {
      return supplier.get();
    } finally {
      logDuration(name, start);
    }
  }

  private static long elapsedMs(long start) {
    return (System.nanoTime() - start) / 1_000_000;
  }

  private static void logExecuting(String name) {
    log.info("{} executing", name);
  }

  private static void logDuration(String name, long start) {
    log.info("{} executed in {} ms", name, elapsedMs(start));
  }
}
