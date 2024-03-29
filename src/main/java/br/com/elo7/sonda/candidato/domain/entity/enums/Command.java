package br.com.elo7.sonda.candidato.domain.entity.enums;

import lombok.Getter;

public enum Command {

    L("LEFT"),
    R("RIGHT"),
    M("MOVE");

    Command(String description) {
        this.description = description;
    }

    @Getter
    private final String description;
}
