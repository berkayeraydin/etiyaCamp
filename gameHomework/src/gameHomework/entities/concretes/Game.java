package gameHomework.entities.concretes;

import gameHomework.entities.abstracts.Entity;

public class Game implements Entity {
	
	private int id;
	private String gameName;
	private double gamePrice;
	
	public Game() {
		super();
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", gameName=" + gameName + ", gamePrice=" + gamePrice + "]";
	}

	public Game(int id, String gameName, double gamePrice) {
		super();
		this.id = id;
		this.gameName = gameName;
		this.gamePrice = gamePrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public double getGamePrice() {
		return gamePrice;
	}

	public void setGamePrice(double gamePrice) {
		this.gamePrice = gamePrice;
	}

}
