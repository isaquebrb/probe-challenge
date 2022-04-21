package br.com.elo7.sonda.candidato.domain.service;

import br.com.elo7.sonda.candidato.domain.entity.Planet;
import br.com.elo7.sonda.candidato.domain.entity.Probe;
import br.com.elo7.sonda.candidato.domain.entity.enums.Command;
import br.com.elo7.sonda.candidato.domain.entity.enums.Direction;
import br.com.elo7.sonda.candidato.domain.exception.MovementException;
import br.com.elo7.sonda.candidato.domain.exception.NotFoundException;
import br.com.elo7.sonda.candidato.infrastructure.repository.ProbeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProbeServiceImplTest {

    @InjectMocks
    private ProbeServiceImpl service;

    @Mock
    private ProbeRepository repository;

    @Test
    void whenSave_shouldReturnProbe() {
        Probe probe = createProbe();

        when(repository.save(any())).thenReturn(probe);

        Probe savedProbe = service.save(probe);

        assertThat(savedProbe.getCoordinateX()).isEqualTo(probe.getCoordinateX());
        assertThat(savedProbe.getCoordinateY()).isEqualTo(probe.getCoordinateY());
        assertThat(savedProbe.getDirection()).isEqualTo(probe.getDirection());
        assertThat(savedProbe.getPlanet()).isEqualTo(probe.getPlanet());
    }

    @Test
    void whenMoveProbeWithAllCommands_shouldMoveProbe() {
        Probe probe = createProbe();
        Integer oldCoordinateY = probe.getCoordinateY();

        List<Command> commands = List.of(Command.M, Command.M, Command.M);

        service.moveProbeWithAllCommands(probe, commands);

        assertThat(probe.getCoordinateY()).isEqualTo(oldCoordinateY + commands.size());
    }

    @Test
    void whenValidateDirectionAvailable_andProbeDoesntExist_doNothing() {
        when(repository.findByCoordinateXAndCoordinateYAndPlanetId(anyInt(), anyInt(), anyLong()))
                .thenReturn(Optional.empty());

        try {
            service.validateDirectionAvailable(1, 2, 10L);
        } catch (Exception e) {
            fail("should pass");
        }
    }

    @Test
    void whenValidateDirectionAvailable_andProbeExist_mustThrowException() {
        when(repository.findByCoordinateXAndCoordinateYAndPlanetId(anyInt(), anyInt(), anyLong()))
                .thenReturn(Optional.of(new Probe()));

        assertThatThrownBy(() -> service.validateDirectionAvailable(1, 2, 10L))
                .isInstanceOf(MovementException.class)
                .hasMessage("Cannot land probe in the coordiantes X = [1] | Y = [2]. Probe id [10] is already there");
    }

    @Test
    void whenFindById_andEntityFound_shouldReturnProbe() {
        Probe probe = createProbe();

        when(repository.findById(anyLong())).thenReturn(Optional.of(probe));

        Probe foundProbe = service.findById(1L);

        assertThat(foundProbe.getCoordinateX()).isEqualTo(probe.getCoordinateX());
        assertThat(foundProbe.getCoordinateY()).isEqualTo(probe.getCoordinateY());
        assertThat(foundProbe.getDirection()).isEqualTo(probe.getDirection());
        assertThat(foundProbe.getPlanet()).isEqualTo(probe.getPlanet());
    }

    @Test
    void whenFindById_andEntityNotFound_mustThrowException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Couldn't find probe with id 1");
    }

    private Probe createProbe() {
        Planet planet = new Planet();
        planet.setHeight(10);
        planet.setWidth(10);

        Probe probe = new Probe();
        probe.setCoordinateX(20);
        probe.setCoordinateY(10);
        probe.setDirection(Direction.N);
        probe.setPlanet(planet);

        return probe;
    }
}
