package br.com.elo7.sonda.candidato.api.model.response;

import br.com.elo7.sonda.candidato.domain.entity.enums.Direction;
import br.com.elo7.sonda.candidato.domain.entity.Probe;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProbePlanetResponse {

    private Integer x;
    private Integer y;
    private Direction direction;
    private PlanetResponse planet;

    public ProbePlanetResponse(Probe probe) {
        this.x = probe.getCoordinateX();
        this.y = probe.getCoordinateY();
        this.direction = probe.getDirection();
        this.planet = new PlanetResponse(probe.getPlanet());
    }
}
