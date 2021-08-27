package etiyaGameProje.entities;

public class Selling {
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
		return "Selling [id=" + id + ", gamer=" + gamer + ", game=" + game + ", campaing=" + campaing
				+ ", CampaignPrice=" + CampaignPrice + "]";
	}


	public Selling(int id, Gamer gamer, Game game, Campaing campaing) {
		super();
		this.id = id;
		this.gamer = gamer;
		this.game = game;
		this.campaing = campaing;
	}
	
	public Selling(int id, Gamer gamer, Game game) {
		super();
		this.id = id;
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
		
		double result;
		result = ( this.game.getGamePrice() * this.campaing.getDiscount())/100;
		this.CampaignPrice = this.game.getGamePrice() - result ;
		
		return this.CampaignPrice;
	}
}
