package br.com.elo7.sonda.candidato.api.model;

import br.com.elo7.sonda.candidato.domain.entity.Planet;
import lombok.Getter;

import java.util.List;

@Getter
public class PlanetProbeRequest {

    private int width;
    private int height;
    private List<ProbeRequest> probes;

    public Planet toEntity() {
        Planet planet = new Planet();
        planet.setHeight(this.getHeight());
        planet.setWidth(this.getWidth());
        return planet;
    }
}
