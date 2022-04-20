package br.com.elo7.sonda.candidato.dto;

import br.com.elo7.sonda.candidato.model.Command;
import br.com.elo7.sonda.candidato.model.Direction;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;
import lombok.Getter;

import java.util.List;

public class ProbeRequest {

    private int x;
    private int y;
    @Getter
    private Direction direction;
    @Getter
    private List<Command> commands;

    public Probe toEntity(Planet planet) {
        Probe probe = new Probe();
        probe.setPlanet(planet);
        probe.setX(this.x);
        probe.setY(this.y);
        probe.setDirection(this.direction);
        return probe;
    }
}
