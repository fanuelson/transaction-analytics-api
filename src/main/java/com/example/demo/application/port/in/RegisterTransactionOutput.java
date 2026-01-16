package com.example.demo.application.port.in;

import com.example.demo.application.usecase.model.MinuteSummaryStatistics;
import com.example.demo.domain.Transaction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterTransactionOutput {

  private final Transaction transaction;
  private final MinuteSummaryStatistics summary;;

  public static RegisterTransactionOutput of(Transaction transaction, MinuteSummaryStatistics summary) {
    return new RegisterTransactionOutput(transaction, summary);
  }

  public static RegisterTransactionOutput of(Transaction transaction) {
    return of(transaction, null);
  }

}
