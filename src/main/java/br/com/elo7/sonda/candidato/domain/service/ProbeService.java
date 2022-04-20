package br.com.elo7.sonda.candidato.domain.service;

import br.com.elo7.sonda.candidato.domain.entity.enums.Command;
import br.com.elo7.sonda.candidato.domain.entity.Probe;

import java.util.List;

public interface ProbeService {

    Probe save(Probe probe);

    void moveProbeWithAllCommands(Probe probe, List<Command> commands);
}
