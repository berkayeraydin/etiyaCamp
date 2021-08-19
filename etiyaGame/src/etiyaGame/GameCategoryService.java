package etiyaGame;

import java.util.ArrayList;

public class GameCategoryService {
	
	ArrayList<GameCategory> gameCategories = new ArrayList<GameCategory>();
	
	public void add(GameCategory gameCategory) {
		gameCategories.add(gameCategory);
	}
	
	public ArrayList<GameCategory> getAll(){
		
		Game g1 = new Game(1,"CS",100);
		Game g2 = new Game(2,"PUBG",500);
		Game g3 = new Game(1,"Pes 2021",500);
		Game g4 = new Game(2,"Fifa 2021",500);
		Game g5 = new Game(1,"Age of Empires",500);
		
		ArrayList<Game> games = new ArrayList<Game>();
		games.add(g1);
		games.add(g2);
		
		ArrayList<Game> games2 = new ArrayList<Game>();
		games2.add(g3);
		games2.add(g4);
		
		ArrayList<Game> games3 = new ArrayList<Game>();
		games3.add(g5);
		
		GameCategory category = new GameCategory(1,"FPS");
		category.setGames(games);
		
		GameCategory category2 = new GameCategory(2,"Futbol");
		category2.setGames(games2);
		
		GameCategory category3 = new GameCategory(3,"Strateji");
		category3.setGames(games3);
		
		gameCategories.add(category);
		gameCategories.add(category2);
		gameCategories.add(category3);
		
		return this.gameCategories;
	}
	

}
