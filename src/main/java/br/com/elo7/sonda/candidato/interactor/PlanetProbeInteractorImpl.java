package br.com.elo7.sonda.candidato.interactor;


import br.com.elo7.sonda.candidato.dto.PlanetProbeRequest;
import br.com.elo7.sonda.candidato.dto.ProbePlanetResponse;
import br.com.elo7.sonda.candidato.dto.ProbeRequest;
import br.com.elo7.sonda.candidato.model.Command;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.service.PlanetService;
import br.com.elo7.sonda.candidato.service.ProbeService;
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
            moveProbeWithAllCommands(probe, probeRequest);
            return probe;
        }).toList();
    }

    private void moveProbeWithAllCommands(Probe probe, ProbeRequest probeRequest) {
        log.info("Landing new probe on planet [{}]", probe.getPlanet().getId());

        for (Command command : probeRequest.getCommands()) {
            log.info("Moving probe with command [{}] ({})", command.name(), command.getDescription());
            probe.applyCommandToProbe(command);
        }
    }
}
