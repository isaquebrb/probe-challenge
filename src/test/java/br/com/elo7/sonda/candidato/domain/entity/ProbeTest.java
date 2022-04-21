package br.com.elo7.sonda.candidato.domain.entity;

import br.com.elo7.sonda.candidato.domain.entity.enums.Command;
import br.com.elo7.sonda.candidato.domain.entity.enums.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProbeTest {

    @Test
    void should_change_probe_direction_from_N_To_W_when_receive_the_command_L() {
        Probe probe = new Probe();
        probe.setDirection(Direction.N);
        probe.applyCommandToProbe(Command.L);
        assertEquals(Direction.W, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_W_To_S_when_receive_the_command_L() {
        Probe probe = new Probe();
        probe.setDirection(Direction.W);
        probe.applyCommandToProbe(Command.L);
        assertEquals(Direction.S, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_S_To_E_when_receive_the_command_L() {
        Probe probe = new Probe();
        probe.setDirection(Direction.S);
        probe.applyCommandToProbe(Command.L);
        assertEquals(Direction.E, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_E_To_N_when_receive_the_command_L() {
        Probe probe = new Probe();
        probe.setDirection(Direction.E);
        probe.applyCommandToProbe(Command.L);
        assertEquals(Direction.N, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_N_To_E_when_receive_the_command_R() {
        Probe probe = new Probe();
        probe.setDirection(Direction.N);
        probe.applyCommandToProbe(Command.R);
        assertEquals(Direction.E, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_E_To_S_when_receive_the_command_R() {
        Probe probe = new Probe();
        probe.setDirection(Direction.E);
        probe.applyCommandToProbe(Command.R);
        assertEquals(Direction.S, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_S_To_W_when_receive_the_command_R() {
        Probe probe = new Probe();
        probe.setDirection(Direction.S);
        probe.applyCommandToProbe(Command.R);
        assertEquals(Direction.W, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_W_To_N_when_receive_the_command_R() {
        Probe probe = new Probe();
        probe.setDirection(Direction.W);
        probe.applyCommandToProbe(Command.R);
        assertEquals(Direction.N, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_N_To_1_2_N_when_receive_the_command_M() {
        Probe probe = new Probe();
        probe.setCoordinateX(1);
        probe.setCoordinateY(1);
        probe.setDirection(Direction.N);
        probe.applyCommandToProbe(Command.M);
        assertEquals(2, probe.getCoordinateY());
        assertEquals(1, probe.getCoordinateX());
        assertEquals(Direction.N, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_S_To_1_0_S_when_receive_the_command_M() {
        Probe probe = new Probe();
        probe.setCoordinateX(1);
        probe.setCoordinateY(1);
        probe.setDirection(Direction.S);
        probe.applyCommandToProbe(Command.M);
        assertEquals(0, probe.getCoordinateY());
        assertEquals(1, probe.getCoordinateX());
        assertEquals(Direction.S, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_W_To_0_1_W_when_receive_the_command_M() {
        Probe probe = new Probe();
        probe.setCoordinateX(1);
        probe.setCoordinateY(1);
        probe.setDirection(Direction.W);
        probe.applyCommandToProbe(Command.M);
        assertEquals(0, probe.getCoordinateX());
        assertEquals(1, probe.getCoordinateY());
        assertEquals(Direction.W, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_E_To_2_1_E_when_receive_the_command_M() {
        Probe probe = new Probe();
        probe.setCoordinateX(1);
        probe.setCoordinateY(1);
        probe.setDirection(Direction.E);
        probe.applyCommandToProbe(Command.M);
        assertEquals(2, probe.getCoordinateX());
        assertEquals(1, probe.getCoordinateY());
        assertEquals(Direction.E, probe.getDirection());
    }
}
