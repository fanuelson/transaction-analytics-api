package com.example.demo.application.usecase;

import com.example.demo.application.port.in.UpdateTransactionSummaryCommand;
import com.example.demo.application.port.in.UpdateTransactionSummaryOutput;

public interface UpdateTransactionSummaryUseCase {
  UpdateTransactionSummaryOutput execute(UpdateTransactionSummaryCommand command);
}
