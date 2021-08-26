package gameHomework.entities.concretes;

import gameHomework.entities.abstracts.Entity;

public class Game implements Entity {
	
	private int id;
	private String gameName;
	private double gamePrice;
	Campaing campaing;
	private double campaignPrice;
	
	public Game() {
		super();
	}
	
	@Override
	public String toString() {
		return "Game [id=" + id + ", gameName=" + gameName + ", gamePrice=" + gamePrice + ", campaing=" + campaing
				+ ", campaignPrice=" + campaignPrice + "]";
	}

	public Game(int id, String gameName, double gamePrice) {
		super();
		this.id = id;
		this.gameName = gameName;
		this.gamePrice = gamePrice;
	}

	public Game(int id, String gameName, double gamePrice,Campaing campaing) {
		super();
		this.id = id;
		this.gameName = gameName;
		this.gamePrice = gamePrice;
		this.campaing = campaing;
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

	public Campaing getCampaing() {
		return campaing;
	}

	public void setCampaing(Campaing campaing) {
		this.campaing = campaing;
	}

	public double getCampaignPrice() {
		this.campaignPrice = this.gamePrice - (this.gamePrice*this.getCampaing().getDiscount())/100;
		return  campaignPrice;
	}

}
