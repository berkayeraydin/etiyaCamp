package gameHomework.dataAccess.abstracts;

import java.util.ArrayList;

import gameHomework.entities.concretes.Game;
import gameHomework.entities.concretes.Gamer;
import gameHomework.entities.concretes.Selling;

public interface SellingDao {

	void add(Selling selling);
	
	ArrayList<Selling> list();
	
	
}
