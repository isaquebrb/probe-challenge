package br.com.elo7.sonda.candidato.infrastructure.repository;

import br.com.elo7.sonda.candidato.domain.entity.Probe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProbeRepository extends JpaRepository<Probe, Long> {

    Optional<Probe> findByCoordinateXAndCoordinateYAndPlanetId(Integer coordinateX, Integer coordinateY, Long planetId);
}
