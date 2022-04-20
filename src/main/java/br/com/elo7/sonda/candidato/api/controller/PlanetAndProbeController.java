package br.com.elo7.sonda.candidato.api.controller;

import br.com.elo7.sonda.candidato.api.model.PlanetProbeRequest;
import br.com.elo7.sonda.candidato.api.model.ProbePlanetResponse;
import br.com.elo7.sonda.candidato.infrastructure.interactor.PlanetProbeInteractor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/planet-with-probes")
@RequiredArgsConstructor
public class PlanetAndProbeController {

    private final PlanetProbeInteractor planetProbeInteractor;

    @PostMapping
    public ResponseEntity<List<ProbePlanetResponse>> register(@RequestBody PlanetProbeRequest planetProbeRequest) {
        return ResponseEntity.ok(planetProbeInteractor.register(planetProbeRequest));
    }
}
