package br.com.elo7.sonda.candidato.api.model;

import br.com.elo7.sonda.candidato.domain.entity.enums.Command;
import br.com.elo7.sonda.candidato.domain.entity.enums.Direction;
import br.com.elo7.sonda.candidato.domain.entity.Planet;
import br.com.elo7.sonda.candidato.domain.entity.Probe;
import lombok.Getter;

import java.util.List;

@Getter
public class ProbeRequest {

    private int x;
    private int y;
    private Direction direction;
    private List<Command> commands;

    public Probe toEntity(Planet planet) {
        Probe probe = new Probe();
        probe.setPlanet(planet);
        probe.setX(this.getX());
        probe.setY(this.getY());
        probe.setDirection(this.getDirection());
        return probe;
    }
}
