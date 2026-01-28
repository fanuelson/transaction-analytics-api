package com.example.demo.domain.transaction;


import com.example.demo.domain.money.Money;
import com.example.demo.domain.transaction.vo.TransactionId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class DefaultTransaction implements Transaction {

  private TransactionId id;
  private Money amount;
  private LocalDateTime occurredAt;

  public static DefaultTransaction of(Money amount, LocalDateTime occurredAt) {
    return new DefaultTransaction(null, amount, occurredAt);
  }

  public DefaultTransaction withId(TransactionId id) {
    return new DefaultTransaction(id, this.getAmount(), this.getOccurredAt());
  }

}
