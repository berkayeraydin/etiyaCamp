package etiyaGame;

import java.util.ArrayList;

public class GameService {
	
	ArrayList<Game> games = new ArrayList<Game>();
	
	public void addGame(Game game) {
		games.add(game);
	}
	
	public ArrayList<Game> getAllGamers() {
	
		Game g1 = new Game(1,1,"CS",100);
		Game g2 = new Game(2,1,"PUBG",500);
		Game g3 = new Game(3,2,"Pes 2021",500);
		Game g4 = new Game(4,2,"Fifa 2021",500);
		Game g5 = new Game(5,3,"Age of Empires",500);

		this.games.add(g1);
		this.games.add(g2);
		this.games.add(g3);
		this.games.add(g4);
		this.games.add(g5);
		
		return games;
	}
	
}
