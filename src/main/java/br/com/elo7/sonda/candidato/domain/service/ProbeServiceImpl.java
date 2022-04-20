package br.com.elo7.sonda.candidato.domain.service;

import br.com.elo7.sonda.candidato.domain.entity.enums.Command;
import br.com.elo7.sonda.candidato.domain.entity.Probe;
import br.com.elo7.sonda.candidato.infrastructure.repository.ProbeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProbeServiceImpl implements ProbeService {

    private final ProbeRepository probeRepository;

    @Override
    public Probe save(Probe probe) {
        log.info("Saving probe with coordinates X = [{}] and Y = [{}]", probe.getX(), probe.getY());
        return probeRepository.save(probe);
        //todo check if coordinates and planet id already exists
        //todo add index for coordinates
    }

    @Override
    public void moveProbeWithAllCommands(Probe probe, List<Command> commands) {
        log.info("Landing new probe on planet [{}]", probe.getPlanet().getId());

        for (Command command : commands) {
            log.info("Moving probe with command [{}] ({})", command.name(), command.getDescription());
            probe.applyCommandToProbe(command);
        }
    }
}
