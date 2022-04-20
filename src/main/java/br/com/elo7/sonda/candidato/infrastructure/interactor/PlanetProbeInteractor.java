package br.com.elo7.sonda.candidato.infrastructure.interactor;

import br.com.elo7.sonda.candidato.api.model.PlanetProbeRequest;
import br.com.elo7.sonda.candidato.api.model.ProbePlanetResponse;

import java.util.List;

public interface PlanetProbeInteractor {

    List<ProbePlanetResponse> register(PlanetProbeRequest planetProbeRequest);
}
