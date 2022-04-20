package br.com.elo7.sonda.candidato.api.model.request;

import br.com.elo7.sonda.candidato.domain.entity.enums.Command;
import br.com.elo7.sonda.candidato.domain.entity.enums.Direction;
import br.com.elo7.sonda.candidato.domain.entity.Planet;
import br.com.elo7.sonda.candidato.domain.entity.Probe;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class ProbeRequest {

    @NotNull
    private Integer x;
    @NotNull
    private Integer y;
    @NotNull
    private Direction direction;
    private List<Command> commands;

    public Probe toEntity(Planet planet) {
        Probe probe = new Probe();
        probe.setPlanet(planet);
        probe.setCoordinateX(this.getX());
        probe.setCoordinateY(this.getY());
        probe.setDirection(this.getDirection());
        return probe;
    }
}
