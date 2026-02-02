package com.example.demo.infra.repository;

import com.example.demo.domain.transaction.TransactionStatisticsBucket;
import com.example.demo.domain.transaction.repository.TransactionStatisticsRepository;
import com.example.demo.domain.transaction.vo.TransactionStatisticsKey;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.UnaryOperator;
import static java.util.Optional.ofNullable;

@Service
public class TransactionStatisticsRepositoryImpl implements TransactionStatisticsRepository {

  private final ConcurrentMap<Long, TransactionStatisticsBucket> store = new ConcurrentHashMap<>();

  @Override
  public void save(TransactionStatisticsKey key, UnaryOperator<TransactionStatisticsBucket> computer) {
    store.compute(key.value(), (k, v) -> computer.apply(v));
  }

  @Override
  public Optional<TransactionStatisticsBucket> findOne(TransactionStatisticsKey key) {
    return ofNullable(store.get(key.value()));
  }

  @Override
  public List<TransactionStatisticsBucket> findAllAfter(TransactionStatisticsKey key) {
    return store.entrySet()
      .stream()
      .filter(it -> it.getKey() > key.value())
      .map(Map.Entry::getValue)
      .toList();
  }

  @Override
  public long deleteAll() {
    final var size = store.size();
    store.clear();
    return size;
  }

}
