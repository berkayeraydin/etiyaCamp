package etiyaGame;

public class Game {
	
	private int id;
	private String gameName;
	private double gamePrice;
	
	

	@Override
	public String toString() {
		return "Game [id=" + id + ", gameName=" + gameName + ", gamePrice=" + gamePrice + "]";
	}

	// constructors
	Game (int id,String gameName, double gamePrice){
		this.id=id;
		this.gameName=gameName;
		this.gamePrice=gamePrice;
	}
	
	public Game(){
		
	}
	// methods
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	
	
	
}
