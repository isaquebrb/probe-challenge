package br.com.elo7.sonda.candidato.infrastructure.interactor;

import br.com.elo7.sonda.candidato.api.model.request.CommandRequest;
import br.com.elo7.sonda.candidato.api.model.request.PlanetProbeRequest;
import br.com.elo7.sonda.candidato.api.model.request.ProbeRequest;
import br.com.elo7.sonda.candidato.api.model.response.ProbePlanetResponse;
import br.com.elo7.sonda.candidato.domain.entity.Planet;
import br.com.elo7.sonda.candidato.domain.entity.Probe;
import br.com.elo7.sonda.candidato.domain.entity.enums.Command;
import br.com.elo7.sonda.candidato.domain.entity.enums.Direction;
import br.com.elo7.sonda.candidato.domain.service.PlanetService;
import br.com.elo7.sonda.candidato.domain.service.ProbeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlanetAndProbeInteractorImplTest {

    @InjectMocks
    private PlanetAndProbeInteractorImpl interactor;

    @Mock
    private PlanetService planetService;

    @Mock
    private ProbeService probeService;

    private PlanetProbeRequest planetProbeRequest;

    private ProbeRequest probeRequest;

    private CommandRequest commandRequest;

    @BeforeEach
    void setUp() {
        planetProbeRequest = Mockito.mock(PlanetProbeRequest.class);
        probeRequest = Mockito.mock(ProbeRequest.class);
        commandRequest = Mockito.mock(CommandRequest.class);
    }

    @Test
    void whenRegister_shouldReturnProbePlanetResponseList() {
        Probe probe = createProbe();
        Planet planet = probe.getPlanet();

        when(probeRequest.toEntity(any())).thenReturn(createProbe());
        when(planetService.save(any())).thenReturn(planet);
        when(planetProbeRequest.getProbes()).thenReturn(List.of(probeRequest));

        ProbePlanetResponse response = interactor.register(planetProbeRequest).stream()
                .findFirst().orElse(new ProbePlanetResponse());

        assertThat(response.getX()).isEqualTo(probe.getCoordinateX());
        assertThat(response.getY()).isEqualTo(probe.getCoordinateY());
        assertThat(response.getDirection()).isEqualTo(probe.getDirection());
        assertThat(response.getPlanet().getHeight()).isEqualTo(probe.getPlanet().getHeight());
        assertThat(response.getPlanet().getWidth()).isEqualTo(probe.getPlanet().getWidth());
    }

    @Test
    void whenRegisterOnPlanet_shouldReturnProbePlanetResponseList() {
        Probe probe = createProbe();
        Planet planet = probe.getPlanet();

        when(probeRequest.toEntity(any())).thenReturn(createProbe());
        when(planetService.findById(anyLong())).thenReturn(planet);

        ProbePlanetResponse response = interactor.registerOnPlanet(List.of(probeRequest), 1L).stream()
                .findFirst().orElse(new ProbePlanetResponse());

        assertThat(response.getX()).isEqualTo(probe.getCoordinateX());
        assertThat(response.getY()).isEqualTo(probe.getCoordinateY());
        assertThat(response.getDirection()).isEqualTo(probe.getDirection());
        assertThat(response.getPlanet().getHeight()).isEqualTo(probe.getPlanet().getHeight());
        assertThat(response.getPlanet().getWidth()).isEqualTo(probe.getPlanet().getWidth());
    }

    @Test
    void whenMoveProbe_shouldReturnProbePlanetResponse() {
        Probe probe = createProbe();

        when(probeService.findById(anyLong())).thenReturn(probe);
        when(commandRequest.getCommands()).thenReturn(List.of(Command.M, Command.L, Command.R));

        ProbePlanetResponse response = interactor.moveProbe(commandRequest, 1L);

        assertThat(response.getX()).isEqualTo(probe.getCoordinateX());
        assertThat(response.getY()).isEqualTo(probe.getCoordinateY());
        assertThat(response.getDirection()).isEqualTo(probe.getDirection());
        assertThat(response.getPlanet().getHeight()).isEqualTo(probe.getPlanet().getHeight());
        assertThat(response.getPlanet().getWidth()).isEqualTo(probe.getPlanet().getWidth());
    }

    private Probe createProbe() {
        Planet planet = new Planet();
        planet.setHeight(10);
        planet.setWidth(10);

        Probe probe = new Probe();
        probe.setCoordinateX(6);
        probe.setCoordinateY(3);
        probe.setDirection(Direction.N);
        probe.setPlanet(planet);

        return probe;
    }
}
