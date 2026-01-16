package com.example.demo.infra.repository;

import com.example.demo.application.port.out.MinuteSummaryStatRepository;
import com.example.demo.application.usecase.model.MinuteSummaryStatistics;
import com.example.demo.application.usecase.model.MinuteSummaryStatisticsKey;
import org.springframework.stereotype.Repository;
import java.util.*;
import static java.util.Optional.*;

@Repository
public class MinuteSummaryStatRepositoryImpl implements MinuteSummaryStatRepository {

  private final Map<MinuteSummaryStatisticsKey, MinuteSummaryStatistics> store = new HashMap<>();

  @Override
  public Collection<MinuteSummaryStatistics> findAll() {
    return store.values();
  }

  @Override
  public Set<MinuteSummaryStatisticsKey> findAllKeys() {
    return store.keySet();
  }

  @Override
  public Optional<MinuteSummaryStatistics> findByKey(MinuteSummaryStatisticsKey key) {
    return ofNullable(store.get(key));
  }

  @Override
  public MinuteSummaryStatistics saveOrUpdate(MinuteSummaryStatistics stat) {
    final var key = stat.getKey();
    final var current = findByKey(key)
      .map(MinuteSummaryStatistics.accepting(stat.getSummary()))
      .orElse(stat);
    store.put(key, current);
    return store.get(key);
  }

  public long deleteAll() {
    final var size = store.size();
    store.clear();
    return size;
  }

}
