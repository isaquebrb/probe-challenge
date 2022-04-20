package br.com.elo7.sonda.candidato.infrastructure.interactor;

import br.com.elo7.sonda.candidato.api.model.PlanetProbeRequest;
import br.com.elo7.sonda.candidato.api.model.ProbePlanetResponse;
import br.com.elo7.sonda.candidato.api.model.ProbeRequest;

import java.util.List;

public interface PlanetProbeInteractor {

    List<ProbePlanetResponse> register(PlanetProbeRequest planetProbeRequest);

    List<ProbePlanetResponse> registerOnPlanet(List<ProbeRequest> probeRequests, Long planetId);
}
