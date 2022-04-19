package br.com.elo7.sonda.candidato.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Planet {

	@Id
	private Long id;
	private int width;
	private int height;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
