package kodlama.io.business.abstracts;

import java.util.List;

import kodlama.io.entities.concretes.Category;

public interface CategoryService {
	
	List<Category> getAll();
	void add(Category category);
}
