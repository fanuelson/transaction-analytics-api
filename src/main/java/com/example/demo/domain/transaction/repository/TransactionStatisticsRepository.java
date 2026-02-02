package com.example.demo.domain.transaction.repository;

import com.example.demo.domain.transaction.TransactionStatisticsBucket;
import com.example.demo.domain.transaction.vo.TransactionStatisticsKey;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

public interface TransactionStatisticsRepository {
  void save(TransactionStatisticsKey key, UnaryOperator<TransactionStatisticsBucket> computer);
  Optional<TransactionStatisticsBucket> findOne(TransactionStatisticsKey key);
  List<TransactionStatisticsBucket> findAllAfter(TransactionStatisticsKey key);
  long deleteAll();
}
