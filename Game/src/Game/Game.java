package Game;

public class Game {
	
	private String gameName;
	private String gameType;
	private double gamePrice;
	
	// constructors
	Game (String gameName,String gameType, double gamePrice){
		this.gameName=gameName;
		this.gameType=gameType;
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
	
	
	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	
	public double getGamePrice() {
		return gamePrice;
	}

	public void setGamePrice(double gamePrice) {
		this.gamePrice = gamePrice;
	}

	
	
	
	
	
}
