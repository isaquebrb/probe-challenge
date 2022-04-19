package br.com.elo7.sonda.candidato.persistence;

import br.com.elo7.sonda.candidato.model.Probe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProbeRepository extends JpaRepository<Probe, Long> {
}
