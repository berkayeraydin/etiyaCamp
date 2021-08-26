package gameHomework.dataAccess.abstracts;

import java.util.ArrayList;

import gameHomework.entities.concretes.Game;
import gameHomework.entities.concretes.Gamer;
import gameHomework.entities.concretes.Selling;

public interface SellingDao {

	void campaingSellingAdd(Game game, Gamer gamer);
	
	void sellingAdd(Game game,Gamer gamer);
	
	ArrayList<Selling> list();
	
	
}
