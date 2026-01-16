package com.example.demo.infra.repository;

import com.example.demo.application.port.in.TransactionQuery;
import com.example.demo.application.port.out.TransactionRepository;
import com.example.demo.domain.model.Transaction;
import com.example.demo.domain.model.TransactionId;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import static java.util.Objects.*;

@Service
public class TransactionRepositoryImpl implements TransactionRepository {

  private final ConcurrentMap<String, Transaction> store = new ConcurrentHashMap<>();

  @Override
  public Transaction save(Transaction transaction) {
    final var id = TransactionId.of(generateId());
    final var createdTransaction = transaction.withId(id);
    final var existing = store.putIfAbsent(createdTransaction.getId().value(), createdTransaction);
    return isNull(existing) ? createdTransaction : existing;
  }

  @Override
  public List<Transaction> findAll(TransactionQuery searchQuery) {
    final var from = searchQuery.getFrom();
    final var to = searchQuery.getTo();
    return store.values().stream()
      .filter(Transaction.isBetween(from, to))
      .toList();
  }

  @Override
  public long deleteAll() {
    final var size = store.size();
    store.clear();
    return size;
  }

  private static String generateId() {
    return UUID.randomUUID().toString();
  }

}
