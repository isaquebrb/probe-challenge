package br.com.elo7.sonda.candidato.infrastructure.interactor;


import br.com.elo7.sonda.candidato.api.model.request.CommandRequest;
import br.com.elo7.sonda.candidato.api.model.request.PlanetProbeRequest;
import br.com.elo7.sonda.candidato.api.model.request.ProbeRequest;
import br.com.elo7.sonda.candidato.api.model.response.ProbePlanetResponse;
import br.com.elo7.sonda.candidato.domain.entity.Planet;
import br.com.elo7.sonda.candidato.domain.entity.Probe;
import br.com.elo7.sonda.candidato.domain.service.PlanetService;
import br.com.elo7.sonda.candidato.domain.service.ProbeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlanetAndProbeInteractorImpl implements PlanetAndProbeInteractor {

    private final PlanetService planetService;
    private final ProbeService probeService;

    @Override
    @Transactional
    public List<ProbePlanetResponse> register(PlanetProbeRequest planetProbeRequest) {
        Planet planet = planetService.save(planetProbeRequest.toEntity());

        log.info("Converting requests to probes");
        List<Probe> probes = planetProbeRequest.getProbes().stream()
                .map(probeRequest -> convertAndMoveProbes(probeRequest, planet)).toList();

        probes.forEach(this::validateAndSaveProbe);

        return probes.stream().map(ProbePlanetResponse::new).toList();
    }

    @Override
    public List<ProbePlanetResponse> registerOnPlanet(List<ProbeRequest> probeRequests, Long planetId) {
        Planet planet = planetService.findById(planetId);

        log.info("Converting requests to probes");
        List<Probe> probes = probeRequests.stream()
                .map(probeRequest -> convertAndMoveProbes(probeRequest, planet)).toList();

        probes.forEach(this::validateAndSaveProbe);

        return probes.stream().map(ProbePlanetResponse::new).toList();
    }

    @Override
    public ProbePlanetResponse moveProbe(CommandRequest commandRequest, Long probeId) {
        Probe probe = probeService.findById(probeId);

        probeService.moveProbeWithAllCommands(probe, commandRequest.getCommands());

        validateAndSaveProbe(probe);
        return new ProbePlanetResponse(probe);
    }

    private Probe convertAndMoveProbes(ProbeRequest probeRequest, Planet planet) {
        Probe probe = probeRequest.toEntity(planet);
        probeService.moveProbeWithAllCommands(probe, probeRequest.getCommands());
        return probe;
    }

    private void validateAndSaveProbe(Probe probe) {
        probeService.validateDirectionAvailable(probe.getCoordinateX(), probe.getCoordinateY(), probe.getPlanet().getId());
        probeService.save(probe);
    }
}
