package com.example.demo.application.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionEventPublisher {

  private final ApplicationEventPublisher publisher;

  public void publish(TransactionCreatedEvent event) {
    publisher.publishEvent(event);
  }
}
