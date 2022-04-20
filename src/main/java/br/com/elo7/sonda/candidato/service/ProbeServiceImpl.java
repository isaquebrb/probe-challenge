package br.com.elo7.sonda.candidato.service;

import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.persistence.ProbeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
