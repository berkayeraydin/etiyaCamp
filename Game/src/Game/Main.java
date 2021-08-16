package Game;

public class Main {

	public static void main(String[] args) {
	
		GameService gameService = new GameService();

		Game g1 = new Game("CS","FPS",100);
		Game g2 = new Game("Fifa2021","Futbol",500);
		gameService.addGame(g1);
		gameService.addGame(g2);
		System.out.println("------------");
		
		for (Game games : gameService.getAllGamers()) {
			//System.out.println(games);
			System.out.println(games.getGameName()+" "+games.getGameType()+" "+games.getGamePrice());
		}
		
		//Game[] resultGame = gameService.getAllGames();
		
		/*for(Game game:gameService.getAllGames()) {
			System.out.println(game.getGameName());
			System.out.println(game.getGamePrice());
			System.out.println(game.getGameType());
			System.out.println("------------");
		}*/
		
		System.out.println("------------");
		
		UserService userService = new UserService();
		
		User u1 = new User("Berkay","ERAYDIN",18);
		User u2 = new User("Sena","ERAYDIN",20);
		userService.userAdd(u1);
		userService.userAdd(u2);
		
		System.out.println("------------");
		
	    for (User users : userService.getAllUsers()) {
	    	
            System.out.println(users.getUserFirstName()+" "+users.getUserLAstName()+" "+users.getUserAge());
        }
	    System.out.println("------------");
		System.out.println(u1.getUserFirstName());
		
		
	}

	
}
