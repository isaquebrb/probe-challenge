package br.com.elo7.sonda.candidato.service;

import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.persistence.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
