package br.com.elo7.sonda.candidato.domain.service;

import br.com.elo7.sonda.candidato.domain.entity.Probe;
import br.com.elo7.sonda.candidato.domain.entity.enums.Command;
import br.com.elo7.sonda.candidato.domain.exception.MovementException;
import br.com.elo7.sonda.candidato.domain.exception.NotFoundException;
import br.com.elo7.sonda.candidato.infrastructure.repository.ProbeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProbeServiceImpl implements ProbeService {

    private final ProbeRepository probeRepository;

    @Override
    public Probe save(Probe probe) {
        log.info("Saving probe with coordinates X = [{}] and Y = [{}]", probe.getCoordinateX(), probe.getCoordinateY());
        return probeRepository.save(probe);
        //todo cache no planet findbyId
    }

    @Override
    public void moveProbeWithAllCommands(Probe probe, List<Command> commands) {
        log.info("Landing new probe on planet [{}]", probe.getPlanet().getId());

        for (Command command : commands) {
            log.info("Moving probe with command [{}] ({})", command.name(), command.getDescription());
            probe.applyCommandToProbe(command);
        }
    }

    @Override
    public void validateDirectionAvailable(Integer xCoordinate, Integer yCoordinate, Long planetId) {
        log.info("Checking if probe direction is available on planet [{}]", planetId);
        Optional<Probe> existingProbe = probeRepository
                .findByCoordinateXAndCoordinateYAndPlanetId(xCoordinate, yCoordinate, planetId);

        if (existingProbe.isPresent()) {
            throw new MovementException(String.format(
                    "Cannot land probe in the coordiantes X = [%d] | Y = [%d]. Probe id [%d] is already there",
                    xCoordinate, yCoordinate, planetId));
        }
    }

    @Override
    public Probe findById(Long probeId) {
        return probeRepository.findById(probeId)
                .orElseThrow(() -> new NotFoundException("Couldn't find probe with id " + probeId));
    }
}
