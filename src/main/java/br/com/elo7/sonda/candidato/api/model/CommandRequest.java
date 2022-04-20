package br.com.elo7.sonda.candidato.api.model;

import br.com.elo7.sonda.candidato.domain.entity.enums.Command;
import lombok.Getter;

import java.util.List;

@Getter
public class CommandRequest {

    private List<Command> commands;
}
