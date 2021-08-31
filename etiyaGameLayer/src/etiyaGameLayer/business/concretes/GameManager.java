package etiyaGameLayer.business.concretes;

import java.util.List;

import etiyaGameLayer.business.abstracts.GameCheckService;
import etiyaGameLayer.business.abstracts.GameService;
import etiyaGameLayer.dataAccess.abstracts.GameDao;
import etiyaGameLayer.entities.concretes.Game;

public class GameManager implements GameService {
	
	GameDao gameDao;
	GameCheckService gameCheckService;
	
	public GameManager(GameDao gameDao, GameCheckService gameCheckService) {
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
		this.gameDao.delete(game);
		
	}

	@Override
	public List<Game> getAll() {
		// TODO Auto-generated method stub
		return this.gameDao.getAll();
	}
	
}
