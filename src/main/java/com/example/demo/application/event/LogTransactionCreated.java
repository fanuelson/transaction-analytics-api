package com.example.demo.application.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogTransactionCreated {

  @Async
  @EventListener
  public void onTransactionCreated(TransactionCreatedEvent event) {
    log.info("Created: {}", event);
  }

}
