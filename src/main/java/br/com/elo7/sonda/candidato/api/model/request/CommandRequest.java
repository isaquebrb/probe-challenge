package br.com.elo7.sonda.candidato.api.model.request;

import br.com.elo7.sonda.candidato.domain.entity.enums.Command;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class CommandRequest {

    @NotEmpty
    private List<Command> commands;
}
