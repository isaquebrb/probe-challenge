package br.com.elo7.sonda.candidato.interactor;

import br.com.elo7.sonda.candidato.dto.PlanetProbeRequest;
import br.com.elo7.sonda.candidato.dto.ProbePlanetResponse;

import java.util.List;

public interface PlanetProbeInteractor {

    List<ProbePlanetResponse> register(PlanetProbeRequest planetProbeRequest);
}
