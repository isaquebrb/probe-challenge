package br.com.elo7.sonda.candidato.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CacheConfig {

    @Scheduled(fixedRate = 300000) //5 minutes cache
    @CacheEvict(cacheNames = "planet", allEntries = true)
    public void clearPlanetCache() {
        log.info("Clearing application cache for planets");
    }
}
