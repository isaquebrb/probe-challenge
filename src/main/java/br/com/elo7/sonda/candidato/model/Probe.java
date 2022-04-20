package br.com.elo7.sonda.candidato.model;

import br.com.elo7.sonda.candidato.exception.CommandException;
import br.com.elo7.sonda.candidato.exception.MovementException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
@Setter
public class Probe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Getter
    private Integer x;

    @Getter
    private Integer y;

    @Getter
    @Enumerated(value = EnumType.STRING)
    private Direction direction;

    @Getter
    @ManyToOne
    @JoinColumn(name = "planet_id") //todo lazy? validar no final da entrega
    private Planet planet;

    public void moveProbeForward() {
        int newX = this.getX();
        int newY = this.getY();
        switch (this.getDirection()) {
            case N -> newY++;
            case W -> newX--;
            case S -> newY--;
            case E -> newX++;
            default -> throw new MovementException("Invalid direction. Cannot move probe forward");
        }
        log.info("Probe moved forward. Coordinates: X = {} | Y = {}", newX, newY);
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

    public void applyCommandToProbe(Command command) {
        //todo rever comandos, direção está incorreta
        switch (command) {
            case R -> this.turnProbeRight();
            case L -> this.turnProbeLeft();
            case M -> this.moveProbeForward();
            default -> throw new CommandException("Invalid command [" + command.name() + "] cannot move probe");
        }
    }
}
