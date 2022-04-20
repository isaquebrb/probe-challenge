package br.com.elo7.sonda.candidato.domain.service;

import br.com.elo7.sonda.candidato.domain.entity.Probe;
import br.com.elo7.sonda.candidato.domain.entity.enums.Command;

import java.util.List;

public interface ProbeService {

    Probe save(Probe probe);

    void moveProbeWithAllCommands(Probe probe, List<Command> commands);

    void validateDirectionAvailable(Integer xCoordinate, Integer yCoordinate, Long planetId);
}
