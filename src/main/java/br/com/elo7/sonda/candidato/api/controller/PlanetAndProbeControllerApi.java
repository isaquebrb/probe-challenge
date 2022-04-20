package br.com.elo7.sonda.candidato.api.controller;

import br.com.elo7.sonda.candidato.api.model.request.CommandRequest;
import br.com.elo7.sonda.candidato.api.model.request.PlanetProbeRequest;
import br.com.elo7.sonda.candidato.api.model.request.ProbeRequest;
import br.com.elo7.sonda.candidato.api.model.response.ProbePlanetResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

public interface PlanetAndProbeControllerApi {

    @Operation(summary = "Register a planet and its multiple probes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planet and its probes registered",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProbePlanetResponse.class)))}),
            @ApiResponse(responseCode = "400", description = "Error to register planet and probes", content = @Content)})
    ResponseEntity<List<ProbePlanetResponse>> register(@Valid PlanetProbeRequest planetProbeRequest);

    @Operation(summary = "Register a new probe on planet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Probe registered on planet",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProbePlanetResponse.class)))}),
            @ApiResponse(responseCode = "400", description = "Error to register probe on planet", content = @Content),
            @ApiResponse(responseCode = "404", description = "Planet not found", content = @Content)})
    ResponseEntity<List<ProbePlanetResponse>> registerOnPlanet(List<@Valid ProbeRequest> probeRequests,
                                                               Long planetId);

    @Operation(summary = "Move a probe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Probe moved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProbePlanetResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Error to move probe", content = @Content),
            @ApiResponse(responseCode = "404", description = "Probe not found", content = @Content)})
    ResponseEntity<ProbePlanetResponse> moveProbe(CommandRequest commandRequest,
                                                  Long probeId);
}
