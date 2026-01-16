package com.example.demo.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class TransactionQuery {
  private final LocalDateTime from;
  private final LocalDateTime to;
}
