package Game;

import java.util.ArrayList;

public class GameService {
	
	ArrayList<Game> games = new ArrayList<Game>();
	
	
	public void addGame(Game game) {
		System.out.println("Basariyla oyun eklendi");
		games.add(game);
	}
	
	public ArrayList<Game> getAllGamers() {
		
		return games;
	}
	
}
