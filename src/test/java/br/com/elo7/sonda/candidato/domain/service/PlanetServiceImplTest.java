package br.com.elo7.sonda.candidato.domain.service;

import br.com.elo7.sonda.candidato.domain.entity.Planet;
import br.com.elo7.sonda.candidato.domain.exception.NotFoundException;
import br.com.elo7.sonda.candidato.infrastructure.repository.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlanetServiceImplTest {

    @InjectMocks
    private PlanetServiceImpl service;

    @Mock
    private PlanetRepository repository;

    @Test
    void whenSave_shouldReturnPlanet() {
        Planet planet = createPlanet();

        when(repository.save(any())).thenReturn(planet);

        Planet savedPlanet = service.save(planet);

        assertThat(savedPlanet.getHeight()).isEqualTo(planet.getHeight());
        assertThat(savedPlanet.getWidth()).isEqualTo(planet.getWidth());
    }

    @Test
    void whenFindById_andEntityFound_shouldReturnPlanet() {
        Planet planet = createPlanet();

        when(repository.findById(anyLong())).thenReturn(Optional.of(planet));

        Planet foundPlaned = service.findById(1L);

        assertThat(foundPlaned.getHeight()).isEqualTo(planet.getHeight());
        assertThat(foundPlaned.getWidth()).isEqualTo(planet.getWidth());
    }

    @Test
    void whenFindById_andEntityNotFound_mustThrowException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Couldn't find planet with id 1");
    }

    private Planet createPlanet() {
        Planet planet = new Planet();
        planet.setHeight(10);
        planet.setWidth(10);
        return planet;
    }
}
