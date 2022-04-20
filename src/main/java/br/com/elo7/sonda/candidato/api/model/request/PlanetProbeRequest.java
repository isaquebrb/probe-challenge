package br.com.elo7.sonda.candidato.api.model.request;

import br.com.elo7.sonda.candidato.domain.entity.Planet;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class PlanetProbeRequest {

    @NotNull
    private Integer width;
    @NotNull
    private Integer height;
    @NotEmpty
    private List<@Valid ProbeRequest> probes;

    public Planet toEntity() {
        Planet planet = new Planet();
        planet.setHeight(this.getHeight());
        planet.setWidth(this.getWidth());
        return planet;
    }
}
