package com.example.demo.infra.repository;

import com.example.demo.application.port.in.TransactionQuery;
import com.example.demo.application.port.out.TransactionRepository;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.TransactionId;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionRepositoryImpl implements TransactionRepository {

  private final List<Transaction> values = new ArrayList<>();

  @Override
  public Transaction save(Transaction transaction) {
    final var id = TransactionId.create();
    final var createdTransaction = transaction.withId(id.value());
    values.add(createdTransaction);
    return createdTransaction;
  }

  @Override
  public List<Transaction> findAll(TransactionQuery searchQuery) {
    final var from = searchQuery.getFrom();
    final var to = searchQuery.getTo();
    return values.stream()
      .filter(Transaction.isBetween(from, to))
      .toList();
  }

  @Override
  public long deleteAll() {
    final var size = values.size();
    values.clear();
    return size;
  }

}
