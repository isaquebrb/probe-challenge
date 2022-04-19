package br.com.elo7.sonda.candidato.model;

import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Planet {

    @Id
    private Long id;

    @Setter
    private Integer width;

    @Setter
    private Integer height;
}
