package br.com.elo7.sonda.candidato.service;

import br.com.elo7.sonda.candidato.dto.InputDTO;
import br.com.elo7.sonda.candidato.dto.ProbeDTO;
import br.com.elo7.sonda.candidato.model.Command;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.persistence.Planets;
import br.com.elo7.sonda.candidato.persistence.Probes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProbeService {

    private final Planets planets;
    private final Probes probes;

    public List<Probe> landProbes(InputDTO input) {
        Planet planet = concertPlanet(input);
        planets.save(planet);

        List<Probe> convertedProbes = convertAndMoveProbes(input, planet);
        convertedProbes.forEach(probe -> probes.save(probe));

        return convertedProbes;
    }

    void applyCommandToProbe(Probe probe, char command) {
        switch (command) {
            case Command.R:
                probe.turnProbeRight();
                break;
            case Command.L:
                probe.turnProbeLeft();
                break;
            case Command.M:
                probe.moveProbeForward();
                break;
        }
    }

    private List<Probe> convertAndMoveProbes(InputDTO input, Planet planet) {
        return input.getProbes()
                .stream().map(probeDto -> {
                    Probe probe = convertProbe(probeDto, planet);
                    moveProbeWithAllCommands(probe, probeDto);
                    return probe;
                }).collect(Collectors.toList());
    }

    private void moveProbeWithAllCommands(Probe probe, ProbeDTO probeDTO) {
        for (char command : probeDTO.getCommands().toCharArray()) {
            applyCommandToProbe(probe, command);
        }
    }

    private Probe convertProbe(ProbeDTO probeDto, Planet planet) {
        Probe probe = new Probe();
        probe.setPlanet(planet);
        probe.setX(probeDto.getX());
        probe.setY(probeDto.getY());
        probe.setDirection(probeDto.getDirection());
        return probe;
    }

    private Planet concertPlanet(InputDTO input) {
        Planet planet = new Planet();
        planet.setHeight(input.getHeight());
        planet.setWidth(input.getWidth());
        return planet;
    }
}
