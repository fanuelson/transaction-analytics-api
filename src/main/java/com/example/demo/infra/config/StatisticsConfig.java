package com.example.demo.infra.config;

import com.example.demo.application.port.out.TimeRangeConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "statistics-config")
public class StatisticsConfig implements TimeRangeConfig {
  private Long defaultTimeRangeInSeconds = 60L;
}
