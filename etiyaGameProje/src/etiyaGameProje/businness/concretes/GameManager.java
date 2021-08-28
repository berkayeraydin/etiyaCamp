package etiyaGameProje.businness.concretes;

import java.util.ArrayList;

import etiyaGameProje.businness.abstracts.GameCheckService;
import etiyaGameProje.businness.abstracts.GameService;
import etiyaGameProje.businness.abstracts.GamerService;
import etiyaGameProje.dataAccess.abstracts.GameDao;
import etiyaGameProje.entities.Game;
import etiyaGameProje.entities.Gamer;

public class GameManager implements GameService{

	GameDao gameDao;
	GameCheckService gameCheckService;
	
	public GameManager(GameDao gameDao,GameCheckService gameCheckService) {
		super();
		this.gameDao = gameDao;
		this.gameCheckService = gameCheckService;
	}

	@Override
	public void add(Game game) {
		if(gameCheckService.approval(game)==true) {
			this.gameDao.add(game);
		}
		
		
	}

	@Override
	public void update(Game game) {
		this.gameDao.update(game);
		
	}

	@Override
	public void remove(Game game) {
		this.gameDao.remove(game);
		
	}

	@Override
	public ArrayList<Game> gameList() {
		
		return this.gameDao.list();
	}


}
