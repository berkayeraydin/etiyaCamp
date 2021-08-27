package etiyaGameProje.entities;

public class Game {
	
	@Override
	public String toString() {
		return "Game [id=" + id + ", gameName=" + gameName + ", type=" + type + ", gamePrice=" + gamePrice
				+ ", gamePoint=" + gamePoint + "]";
	}

	private int id;
	private String gameName;
	private String type;
	private double gamePrice;
	private double gamePoint;
	
	public Game() {
		super();
	}
	
	public Game(int id, String gameName, String type, double gamePrice, double gamePoint) {
		super();
		this.id = id;
		this.gameName = gameName;
		this.type = type;
		this.gamePrice = gamePrice;
		this.gamePoint=gamePoint;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getGamePrice() {
		return gamePrice;
	}
	public void setGamePrice(double gamePrice) {
		this.gamePrice = gamePrice;
	}

	public double getGamePoint() {
		return gamePoint;
	}

	public void setGamePoint(double gamePoint) {
		this.gamePoint = gamePoint;
	}
}
