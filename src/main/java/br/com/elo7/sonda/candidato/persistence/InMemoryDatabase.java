package br.com.elo7.sonda.candidato.persistence;

import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Component
class InMemoryDatabase {
    /*private static Map<Planet, List<Probe>> probesPerPlanet = new HashMap<>();

    @Repository
    public class PlanetDAO implements Planets {
        public void save(Planet planet) {
            planet.setId(generatePlanetId());
            probesPerPlanet.put(planet, Collections.emptyList());
        }

        private Long generatePlanetId() {
            return (long) probesPerPlanet.size() + 1;
        }

        public Optional<Planet> findById(int id) {
            return probesPerPlanet.keySet()
                    .stream()
                    //.filter(planet -> planet.getId() == id)
                    .findFirst();
        }
    }

    @Repository
    public class ProbeDAO implements Probes {
        @Override
        public void save(Probe probe) {
            List<Probe> probes = probesPerPlanet.get(probe.getPlanet());
            probe.setId(generateProbeId(probe, probes.size()));
            probes.add(probe);
        }

        private int generateProbeId(Probe probe, int size) {
            return 7 * (size + 1) + 11;// * probe.getPlanet().getId();
        }

        @Override
        public Optional<Probe> findById(int id) {
            return probesPerPlanet.entrySet().stream().flatMap(
                    entry -> entry.getValue()
                            .stream()
                            .filter(probe -> probe.getId() == id)
            ).findFirst();
        }
    }*/
}
