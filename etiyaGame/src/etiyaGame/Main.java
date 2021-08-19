package etiyaGame;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		System.out.println("------ KAYIT EDILEN GAME OYUNLARINI ARRAYLISTTEN CEKME ------");
		
		GameService gameService = new GameService();
		
		//System.out.println(gameService.getAllGamers());
		
		for (Game game : gameService.getAllGamers()) {
			System.out.println(game);
		}
		
		System.out.println();
		System.out.println("------ GAME KATEGORI AYIRMA ------");
		
		GameCategoryService gameCategoryService = new GameCategoryService();
		
		//System.out.println(gameCategoryService.getAll());
		
		for (GameCategory gameCategory : gameCategoryService.getAll()) {
			System.out.println(gameCategory);
		}
		
		System.out.println();
		System.out.println("------ KAYIT EDILEN USER KISILERINI ARRAYLISTTEN CEKME ------");
		
		UserService userService = new UserService();
		
		User u1 = new User("Berkay","ERAYDIN",18);
		User u2 = new User("Sena","ERAYDIN",13);
		userService.userAdd(u1);
		userService.userAdd(u2);
		
	    for (User user : userService.getAllUsers()) {
	    	
            System.out.println(user);
        }
	    
	    //Game[] resultGame = gameService.getAllGames();
	    
		/*Game g1 = new Game(1,"CS",100);
		Game g2 = new Game(2,"PUBG",500);
		Game g3 = new Game(3,"Pes 2021",500);
		Game g4 = new Game(4,"Fifa 2021",500);
		Game g5 = new Game(5,"Age of Empires",500);
		gameService.addGame(g1);
		gameService.addGame(g2);
		gameService.addGame(g3);
		gameService.addGame(g4);
		gameService.addGame(g5);
		for (Game games : gameService.getAllGamers()) {
			//System.out.println(games);
			System.out.println(games);
		}*/
	    
	    /*for(Game game:gameService.getAllGames()) {
		System.out.println(game.getGameName());
		System.out.println(game.getGamePrice());
		System.out.println(game.getGameType());
		System.out.println("------------");
		}*/
	    
	    
	}	
}
