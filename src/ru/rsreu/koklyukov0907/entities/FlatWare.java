package ru.rsreu.koklyukov0907.entities;

public class FlatWare {

	private int id;
	private String origin;
	private FlatWareTypes type;
	private boolean value;
	private Visual visual;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public FlatWareTypes getType() {
		return type;
	}

	public void setType(FlatWareTypes type) {
		this.type = type;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public Visual getVisual() {
		return visual;
	}

	public void setVisual(Visual visual) {
		this.visual = visual;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public FlatWare() {

	}
}
