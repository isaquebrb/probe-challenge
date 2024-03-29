package br.com.elo7.sonda.candidato.domain.service;

import br.com.elo7.sonda.candidato.domain.entity.Planet;
import br.com.elo7.sonda.candidato.domain.exception.NotFoundException;
import br.com.elo7.sonda.candidato.infrastructure.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository planetRepository;

    @Override
    public Planet save(Planet planet) {
        log.info("Saving planet with height [{}] and width [{}]", planet.getHeight(), planet.getWidth());
        return planetRepository.save(planet);
    }

    @Override
    @Cacheable("planet")
    public Planet findById(Long planetId) {
        return planetRepository.findById(planetId)
                .orElseThrow(() -> new NotFoundException("Couldn't find planet with id " + planetId));
    }
}
