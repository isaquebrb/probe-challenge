package br.com.elo7.sonda.candidato.persistence;

import br.com.elo7.sonda.candidato.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
}
