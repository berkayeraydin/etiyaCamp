package gameHomework.business.concretes;

import gameHomework.business.abstracts.GameService;
import gameHomework.dataAccess.abstracts.GameDao;
import gameHomework.entities.concretes.Game;

public class GameManager implements GameService{
	
	GameDao gameDao;
	
	public GameManager(GameDao gameDao) {
		
		this.gameDao = gameDao;
		
	}
	@Override
	public void add(Game game) {
		this.gameDao.add(game);
		
		
	}

	@Override
	public void remove() {
		this.gameDao.remove();
		
	}

	@Override
	public void update() {
		this.gameDao.update();
		
	}

}
