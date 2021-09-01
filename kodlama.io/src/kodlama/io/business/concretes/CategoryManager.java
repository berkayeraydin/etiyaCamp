package kodlama.io.business.concretes;

import java.util.List;

import kodlama.io.business.abstracts.CategoryService;
import kodlama.io.dataAccess.abstracts.CategoryDao;
import kodlama.io.entities.concretes.Category;

public class CategoryManager implements CategoryService {
	
	CategoryDao categoryDao;
	
	
	public CategoryManager(CategoryDao categoryDao) {
		super();
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> getAll() {
		
		return this.categoryDao.getAll();
	}

	@Override
	public void add(Category category) {

		this.categoryDao.add(category);
	}

}
