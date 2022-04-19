package br.com.elo7.sonda.candidato.model;

import br.com.elo7.sonda.candidato.exception.DirectionException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Slf4j
@Entity
@Setter
public class Probe {

    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    @Getter
    private Integer x;

    @Getter
    private Integer y;

    @Getter
    @Enumerated(value = EnumType.STRING)
    private Direction direction;

    private Planet planet;

    public void moveProbeForward() {
        int newX = this.getX();
        int newY = this.getY();
        switch (this.getDirection()) {
            case N -> newY++;
            case W -> newX--;
            case S -> newY--;
            case E -> newX++;
            default -> throw new DirectionException("Wrong direction. Cannot move probe forward");
        }
        log.info("Probe moved forward. Coordinates: X = [{}] - Y = [{}]", newX, newY);
        this.setX(newX);
        this.setY(newY);
    }

    public void turnProbeLeft() {
        Direction newDirection = switch (this.getDirection()) {
            case N -> Direction.W;
            case W -> Direction.S;
            case S -> Direction.E;
            case E -> Direction.N;
        };
        log.info("Probe moved to left. New direction: [{}]", newDirection.getDescription());
        this.setDirection(newDirection);
    }

    public void turnProbeRight() {
        Direction newDirection = switch (this.getDirection()) {
            case N -> Direction.E;
            case E -> Direction.S;
            case S -> Direction.W;
            case W -> Direction.N;
        };
        log.info("Probe moved to rigth. New direction: [{}]", newDirection.getDescription());
        this.setDirection(newDirection);
    }
}
