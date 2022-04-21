package br.com.elo7.sonda.candidato.infrastructure.repository;

import br.com.elo7.sonda.candidato.domain.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
}
