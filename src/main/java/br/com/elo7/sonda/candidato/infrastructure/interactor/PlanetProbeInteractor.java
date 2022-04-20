package br.com.elo7.sonda.candidato.infrastructure.interactor;

import br.com.elo7.sonda.candidato.api.model.request.CommandRequest;
import br.com.elo7.sonda.candidato.api.model.request.PlanetProbeRequest;
import br.com.elo7.sonda.candidato.api.model.response.ProbePlanetResponse;
import br.com.elo7.sonda.candidato.api.model.request.ProbeRequest;

import java.util.List;

public interface PlanetProbeInteractor {

    List<ProbePlanetResponse> register(PlanetProbeRequest planetProbeRequest);

    List<ProbePlanetResponse> registerOnPlanet(List<ProbeRequest> probeRequests, Long planetId);

    ProbePlanetResponse moveProbe(CommandRequest commandRequest, Long probeId);
}
