package br.com.elo7.sonda.candidato.domain.entity;

import br.com.elo7.sonda.candidato.domain.entity.enums.Command;
import br.com.elo7.sonda.candidato.domain.entity.enums.Direction;
import br.com.elo7.sonda.candidato.domain.exception.CommandException;
import br.com.elo7.sonda.candidato.domain.exception.MovementException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
@Getter
@Setter
@Table(name = "probe", indexes = {
        @Index(name = "x_idx", columnList = "x"),
        @Index(name = "y_idx", columnList = "y")
})
public class Probe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "x")
    private Integer coordinateX;

    @Column(name = "y")
    private Integer coordinateY;

    @Enumerated(value = EnumType.STRING)
    private Direction direction;

    @ManyToOne
    @JoinColumn(name = "planet_id")
    private Planet planet;

    public void moveProbeForward() {
        int newX = this.getCoordinateX();
        int newY = this.getCoordinateY();
        switch (this.getDirection()) {
            case N -> newY++;
            case W -> newX--;
            case S -> newY--;
            case E -> newX++;
            default -> throw new MovementException("Invalid direction. Cannot move probe forward");
        }
        log.info("Probe moved forward. Coordinates: X = {} | Y = {}", newX, newY);
        this.setCoordinateX(newX);
        this.setCoordinateY(newY);
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

    public void applyCommandToProbe(Command command) {
        switch (command) {
            case R -> this.turnProbeRight();
            case L -> this.turnProbeLeft();
            case M -> this.moveProbeForward();
            default -> throw new CommandException("Invalid command [" + command.name() + "] cannot move probe");
        }
    }
}
