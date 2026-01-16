package com.example.demo.application.port.out;

import com.example.demo.application.usecase.model.MinuteSummaryStatistics;
import com.example.demo.application.usecase.model.MinuteSummaryStatisticsKey;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface MinuteSummaryStatRepository {
  Collection<MinuteSummaryStatistics> findAll();
  Set<MinuteSummaryStatisticsKey> findAllKeys();
  Optional<MinuteSummaryStatistics> findByKey(MinuteSummaryStatisticsKey key);
  MinuteSummaryStatistics saveOrUpdate(MinuteSummaryStatistics stat);
  long deleteAll();
}
