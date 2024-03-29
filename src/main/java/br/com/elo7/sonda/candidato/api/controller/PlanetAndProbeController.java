package br.com.elo7.sonda.candidato.api.controller;

import br.com.elo7.sonda.candidato.api.model.request.CommandRequest;
import br.com.elo7.sonda.candidato.api.model.request.PlanetProbeRequest;
import br.com.elo7.sonda.candidato.api.model.request.ProbeRequest;
import br.com.elo7.sonda.candidato.api.model.response.ProbePlanetResponse;
import br.com.elo7.sonda.candidato.infrastructure.interactor.PlanetAndProbeInteractor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planet-with-probes")
@RequiredArgsConstructor
public class PlanetAndProbeController implements PlanetAndProbeControllerApi {

    private final PlanetAndProbeInteractor planetAndProbeInteractor;

    @PostMapping
    public ResponseEntity<List<ProbePlanetResponse>> register(@RequestBody PlanetProbeRequest planetProbeRequest) {
        return ResponseEntity.ok(planetAndProbeInteractor.register(planetProbeRequest));
    }

    @PostMapping("/planet/{id}")
    public ResponseEntity<List<ProbePlanetResponse>> registerOnPlanet(@RequestBody List<ProbeRequest> probeRequests,
                                                                      @PathVariable("id") Long planetId) {
        return ResponseEntity.ok(planetAndProbeInteractor.registerOnPlanet(probeRequests, planetId));
    }

    @PatchMapping("/probe/{id}")
    public ResponseEntity<ProbePlanetResponse> moveProbe(@RequestBody CommandRequest commandRequest,
                                                         @PathVariable("id") Long probeId) {
        return ResponseEntity.ok(planetAndProbeInteractor.moveProbe(commandRequest, probeId));
    }
}
