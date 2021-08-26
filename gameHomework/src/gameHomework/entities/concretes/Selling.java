package gameHomework.entities.concretes;

import gameHomework.entities.abstracts.Entity;

public class Selling implements Entity {
	
	private int id;
	private Gamer gamer;
	private Game game;
	private Campaing campaing;
	private double CampaignPrice;
	
	public Selling() {
		super();
	}
	
	@Override
	public String toString() {
		return "Selling [gamer=" + gamer + ", game=" + game + "]";
	}

	public Selling( Gamer gamer, Game game) {
		super();
		this.gamer = gamer;
		this.game = game;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Gamer getGamer() {
		return gamer;
	}

	public void setGamer(Gamer gamer) {
		this.gamer = gamer;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Campaing getCampaing() {
		return campaing;
	}

	public void setCampaing(Campaing campaing) {
		this.campaing = campaing;
	}

	public double getCampaignPrice() {
		
		return this.CampaignPrice;
	}

	
}
