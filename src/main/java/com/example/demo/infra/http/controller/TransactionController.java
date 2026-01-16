package com.example.demo.infra.http.controller;

import com.example.demo.application.port.in.ClearAllTransactionsOutput;
import com.example.demo.application.port.in.ComputeTransactionStatisticsQuery;
import com.example.demo.application.port.in.RegisterTransactionCommand;
import com.example.demo.application.usecase.ClearAllTransactionsUseCase;
import com.example.demo.application.usecase.ComputeTransactionStatisticsUseCase;
import com.example.demo.application.usecase.RegisterTransactionUseCase;
import com.example.demo.domain.model.Money;
import com.example.demo.infra.http.controller.resources.request.RegisterTransactionRequest;
import com.example.demo.infra.http.controller.resources.request.TransactionSummaryQuery;
import com.example.demo.infra.http.controller.resources.response.RegisterTransactionResponse;
import com.example.demo.infra.http.controller.resources.response.TransactionSummaryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TransactionController {

  private final RegisterTransactionUseCase registerTransaction;
  private final ComputeTransactionStatisticsUseCase computeTransactionStatistics;
  private final ClearAllTransactionsUseCase clearAllTransactions;

  private RegisterTransactionResponse register(RegisterTransactionCommand command) {
    final var output = registerTransaction.execute(command);
    final var transaction = output.getTransaction();
    final var amount = transaction.getAmount();
    final var occurredAt = transaction.getOccurredAt();
    log.debug("TRANSACTION_REGISTERED: {}", transaction);
    return RegisterTransactionResponse.from(amount, occurredAt);
  }

  @PostMapping("/transacao")
  @ResponseStatus(HttpStatus.CREATED)
  public RegisterTransactionResponse registerAt(@RequestBody RegisterTransactionRequest request) {
    final var amount = BigDecimal.valueOf(request.getValor());
    final var occurredAt = request.getDataHora();
    final var command = RegisterTransactionCommand.of(Money.of(amount), occurredAt);
    return register(command);
  }

  @PostMapping("/transacao/now")
  @ResponseStatus(HttpStatus.CREATED)
  public RegisterTransactionResponse registerNow(@RequestBody RegisterTransactionRequest request) {
    final var amount = BigDecimal.valueOf(request.getValor());
    final var command = RegisterTransactionCommand.now(Money.of(amount));
    return register(command);
  }

  @GetMapping("/estatistica")
  @ResponseStatus(HttpStatus.OK)
  public TransactionSummaryResponse stats(TransactionSummaryQuery request) {
    final var lastSeconds = request.getLastSeconds();
    final var query = ComputeTransactionStatisticsQuery.of(lastSeconds);
    final var output = computeTransactionStatistics.execute(query);
    log.debug("CALCULATE_STATISTICS: {}", output.getSummary());
    return TransactionSummaryResponse.from(output.getSummary());
  }

  @DeleteMapping("/transacao")
  @ResponseStatus(HttpStatus.OK)
  public ClearAllTransactionsOutput clearAll() {
    final var output = clearAllTransactions.execute();
    log.debug("TOTAL_TRANSACTION_REMOVED: {}", output.getTotalRemoved());
    return output;
  }

}
