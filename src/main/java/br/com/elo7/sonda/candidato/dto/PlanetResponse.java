package br.com.elo7.sonda.candidato.dto;

import br.com.elo7.sonda.candidato.model.Planet;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlanetResponse {

    private Integer width;
    private Integer height;

    public PlanetResponse(Planet planet) {
        this.width = planet.getWidth();
        this.height = planet.getHeight();
    }
}
