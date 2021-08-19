package etiyaGame;

import java.util.ArrayList;

public class GameCategory {
	
	private int id;
	private String categoryName;
	private ArrayList<Game> games;
	
	@Override
	public String toString() {
		return "GameCategory [id=" + id + ", categoryName=" + categoryName + ", games=" + games + "]";
	}

	GameCategory(){
		games = new ArrayList<Game>();
	}

	public GameCategory(int id, String categoryName) {
		this();
		this.id = id;
		this.categoryName = categoryName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}



}
