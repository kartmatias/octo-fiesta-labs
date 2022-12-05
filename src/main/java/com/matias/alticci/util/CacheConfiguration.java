package com.matias.alticci.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {

    @Value("${cache.time.to.live}")
    private Long cacheTimeToLive;

    @Value("${cache.timer.interval}")
    private Long cacheTimerInterval;

    @Bean
    AlticciSimpleCache alticciSimpleCache() {
      return new AlticciSimpleCache<>(cacheTimeToLive, cacheTimerInterval);
    }
}
