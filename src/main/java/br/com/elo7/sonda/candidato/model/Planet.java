package br.com.elo7.sonda.candidato.model;

import lombok.Setter;

import javax.persistence.*;

@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private Integer width;

    @Setter
    private Integer height;
}
