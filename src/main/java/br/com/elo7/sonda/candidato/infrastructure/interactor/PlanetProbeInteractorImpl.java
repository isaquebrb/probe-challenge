package br.com.elo7.sonda.candidato.infrastructure.interactor;


import br.com.elo7.sonda.candidato.api.model.PlanetProbeRequest;
import br.com.elo7.sonda.candidato.api.model.ProbePlanetResponse;
import br.com.elo7.sonda.candidato.domain.entity.Planet;
import br.com.elo7.sonda.candidato.domain.entity.Probe;
import br.com.elo7.sonda.candidato.domain.service.PlanetService;
import br.com.elo7.sonda.candidato.domain.service.ProbeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlanetProbeInteractorImpl implements PlanetProbeInteractor {

    private final PlanetService planetService;
    private final ProbeService probeService;

    @Override
    public List<ProbePlanetResponse> register(PlanetProbeRequest planetProbeRequest) {
        Planet planet = planetService.save(planetProbeRequest.toEntity());

        List<Probe> probes = convertAndMoveProbes(planetProbeRequest, planet);

        probes.forEach(probeService::save);

        return probes.stream().map(ProbePlanetResponse::new).toList();
    }

    private List<Probe> convertAndMoveProbes(PlanetProbeRequest planetProbeRequest, Planet planet) {
        return planetProbeRequest.getProbes().stream().map(probeRequest -> {
            Probe probe = probeRequest.toEntity(planet);
            probeService.moveProbeWithAllCommands(probe, probeRequest.getCommands());
            return probe;
        }).toList();
    }
}
