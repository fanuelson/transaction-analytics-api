package com.example.demo.application.usecase;

import com.example.demo.application.port.in.ComputeTransactionStatisticsOutput;
import com.example.demo.application.port.in.ComputeTransactionStatisticsQuery;

public interface ComputeTransactionStatisticsUseCase {
  ComputeTransactionStatisticsOutput execute(ComputeTransactionStatisticsQuery query);
}
