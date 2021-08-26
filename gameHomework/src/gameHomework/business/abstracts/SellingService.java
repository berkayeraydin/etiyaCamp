package gameHomework.business.abstracts;

import java.util.ArrayList;

import gameHomework.entities.concretes.Campaing;
import gameHomework.entities.concretes.Game;
import gameHomework.entities.concretes.Gamer;
import gameHomework.entities.concretes.Selling;

public interface SellingService {
	
	
	void sales(Selling selling);
	ArrayList<Selling> sellingList();
	
}
