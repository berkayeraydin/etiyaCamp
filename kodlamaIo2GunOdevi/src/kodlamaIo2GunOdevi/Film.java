package kodlamaIo2GunOdevi;

public class Film {
	
	private String filmName;
	private String filmType;
	private double filmPrice;
	private int filmDate;
	
	public Film() {
		
	}
	public Film(String filmName, String filmType, double filmPrice, int filmDate) {
		super();
		this.filmName = filmName;
		this.filmType = filmType;
		this.filmPrice = filmPrice;
		this.filmDate = filmDate;
	}
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	public String getFilmType() {
		return filmType;
	}
	public void setFilmType(String filmType) {
		this.filmType = filmType;
	}
	public double getFilmPrice() {
		return filmPrice;
	}
	public void setFilmPrice(double filmPrice) {
		this.filmPrice = filmPrice;
	}
	public int getFilmDate() {
		return filmDate;
	}
	public void setFilmDate(int filmDate) {
		this.filmDate = filmDate;
	}

}
