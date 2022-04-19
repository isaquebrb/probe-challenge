package br.com.elo7.sonda.candidato.dto;

import br.com.elo7.sonda.candidato.model.Direction;
import lombok.Getter;

public class ProbeDTO {
	private int x; 
	private int y;

	@Getter
	private Direction direction;
	private String commands;

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getCommands() {
		return commands;
	}
	public void setCommands(String commands) {
		this.commands = commands;
	}
}
