package com.example.demo.application.usecase;

import com.example.demo.application.port.in.RegisterTransactionCommand;
import com.example.demo.application.port.in.RegisterTransactionOutput;

public interface RegisterTransactionUseCase {
  RegisterTransactionOutput execute(RegisterTransactionCommand command);
}
