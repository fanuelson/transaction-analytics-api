package com.example.demo.application.port.out;

import com.example.demo.application.port.in.TransactionQuery;
import com.example.demo.domain.Transaction;
import java.util.List;

public interface TransactionRepository {
  Transaction save(Transaction transaction);
  List<Transaction> findAll(TransactionQuery searchQuery);
  long deleteAll();
}
