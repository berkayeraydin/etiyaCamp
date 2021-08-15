package kodlamaIo2GunOdevi;

public class Main {

	public static void main(String[] args) {
		
		FilmService filmService = new FilmService();
		
		Film film1 = new Film("Recep Ivedik","Komedi",15,2017);
		
		filmService.addFilm(film1);
		System.out.println("-------------------------");
		
		for(Film film:filmService.getAllFilm()) {
			System.out.println("Filmin ismi :"+ film.getFilmName());
			System.out.println("Filmin Turu :"+ film.getFilmType());
			System.out.println("Filmin Ucreti :"+ film.getFilmPrice());
			System.out.println("Filmin Cikis Tarihi : "+film.getFilmDate());
			System.out.println("-------------------------");
		}

	}

}




