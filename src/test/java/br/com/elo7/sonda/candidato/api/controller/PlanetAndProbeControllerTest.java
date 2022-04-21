package br.com.elo7.sonda.candidato.api.controller;

import br.com.elo7.sonda.candidato.api.model.request.CommandRequest;
import br.com.elo7.sonda.candidato.api.model.request.PlanetProbeRequest;
import br.com.elo7.sonda.candidato.api.model.request.ProbeRequest;
import br.com.elo7.sonda.candidato.api.model.response.ProbePlanetResponse;
import br.com.elo7.sonda.candidato.domain.entity.Planet;
import br.com.elo7.sonda.candidato.domain.entity.Probe;
import br.com.elo7.sonda.candidato.domain.entity.enums.Command;
import br.com.elo7.sonda.candidato.domain.entity.enums.Direction;
import br.com.elo7.sonda.candidato.infrastructure.interactor.PlanetAndProbeInteractor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlanetAndProbeControllerTest {

    @InjectMocks
    private PlanetAndProbeController controller;

    @Mock
    private PlanetAndProbeInteractor interactor;

    @Test
    void whenRegister_shouldReturnProbePlanetResponse() {
        ProbePlanetResponse response = createResponse();

        when(interactor.register(any())).thenReturn(List.of(response));

        ResponseEntity<List<ProbePlanetResponse>> responseEntity = controller.register(new PlanetProbeRequest());

        ProbePlanetResponse requestResponse = Objects.requireNonNull(responseEntity.getBody())
                .stream().findFirst().orElse(new ProbePlanetResponse());

        assertThat(requestResponse.getX()).isEqualTo(response.getX());
        assertThat(requestResponse.getY()).isEqualTo(response.getY());
        assertThat(requestResponse.getDirection()).isEqualTo(response.getDirection());
        assertThat(requestResponse.getPlanet()).isEqualTo(response.getPlanet());
    }

    @Test
    void whenRegisterOnPlanet_shouldReturnProbePlanetResponse() {
        ProbePlanetResponse response = createResponse();

        when(interactor.registerOnPlanet(any(), anyLong())).thenReturn(List.of(response));

        ResponseEntity<List<ProbePlanetResponse>> responseEntity = controller
                .registerOnPlanet(List.of(new ProbeRequest()), 1L);

        ProbePlanetResponse requestResponse = Objects.requireNonNull(responseEntity.getBody())
                .stream().findFirst().orElse(new ProbePlanetResponse());

        assertThat(requestResponse.getX()).isEqualTo(response.getX());
        assertThat(requestResponse.getY()).isEqualTo(response.getY());
        assertThat(requestResponse.getDirection()).isEqualTo(response.getDirection());
        assertThat(requestResponse.getPlanet()).isEqualTo(response.getPlanet());
    }

    @Test
    void whenMoveProbe_shouldReturnProbePlanetResponse() {
        ProbePlanetResponse response = createResponse();

        when(interactor.moveProbe(any(), anyLong())).thenReturn(response);

        ResponseEntity<ProbePlanetResponse> responseEntity = controller
                .moveProbe(new CommandRequest(), 1L);

        ProbePlanetResponse requestResponse = responseEntity.getBody();

        assert requestResponse != null;

        assertThat(requestResponse.getX()).isEqualTo(response.getX());
        assertThat(requestResponse.getY()).isEqualTo(response.getY());
        assertThat(requestResponse.getDirection()).isEqualTo(response.getDirection());
        assertThat(requestResponse.getPlanet()).isEqualTo(response.getPlanet());
    }

    private ProbePlanetResponse createResponse() {
        Planet planet = new Planet();
        planet.setHeight(10);
        planet.setWidth(10);

        Probe probe = new Probe();
        probe.setCoordinateX(20);
        probe.setCoordinateY(10);
        probe.setDirection(Direction.N);
        probe.setPlanet(planet);

        return new ProbePlanetResponse(probe);
    }

}