package kodlamaIo2GunOdevi;

public class FilmService {
	
	public void addFilm(Film film) {
		System.out.println("Girilen filim eklendi. Filmin ismi : "+ film.getFilmName());
	}
	
	public Film[] getAllFilm() {
		Film film1 = new Film("Recep Ivedik","Komedi",15,2017);
		Film film2 = new Film("Avatar","Aksiyon",15,2011);
		Film film3 = new Film("Hizi ve Ofkeli","Aksiyon",15,2008);
		
		Film[] film = {film1,film2,film3};
		return film;
	}

}