package br.com.elo7.sonda.candidato.dto;

import br.com.elo7.sonda.candidato.model.Planet;
import lombok.Getter;

import java.util.List;

public class PlanetProbeRequest {

    private int width;
    private int height;
    @Getter
    private List<ProbeRequest> probes;

    public Planet toEntity() {
        Planet planet = new Planet();
        planet.setHeight(this.height);
        planet.setWidth(this.width);
        return planet;
    }
}
