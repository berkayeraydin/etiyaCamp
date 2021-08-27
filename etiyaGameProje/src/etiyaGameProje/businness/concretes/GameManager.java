package etiyaGameProje.businness.concretes;

import etiyaGameProje.businness.abstracts.GameService;
import etiyaGameProje.businness.abstracts.GamerService;
import etiyaGameProje.dataAccess.abstracts.GameDao;
import etiyaGameProje.entities.Game;
import etiyaGameProje.entities.Gamer;

public class GameManager implements GameService{

	GameDao gameDao;
	
	public GameManager(GameDao gameDao) {
		super();
		this.gameDao = gameDao;
	}

	@Override
	public void add(Game game) {
		this.gameDao.add(game);
		
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
	public void list() {
		this.gameDao.list();
		
	}

}
