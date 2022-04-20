package br.com.elo7.sonda.candidato.domain.entity.enums;

import lombok.Getter;

public enum Direction {

    N("NORTH"),
    W("WEST"),
    E("EAST"),
    S("SOUTH");

    Direction(String description) {
        this.description = description;
    }

    @Getter
    private final String description;

}
