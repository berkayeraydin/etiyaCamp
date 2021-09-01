package kodlama.io.dataAccess.concretes.hibernate;

import java.util.ArrayList;
import java.util.List;

import kodlama.io.dataAccess.abstracts.CategoryDao;
import kodlama.io.entities.concretes.Category;

public class HibernateCategoryDao implements CategoryDao{
	
	List<Category> categorys = new ArrayList<Category>();
	
	@Override
	public List<Category> getAll() {
		return this.categorys;
	}

	@Override
	public void add(Category entity) {
		this.categorys.add(entity);
	}

	@Override
	public void update(Category entity) {
		
		System.out.println("HibernateCategoryDao ile Guncellendi");
	}

	@Override
	public void remova(Category entity) {
		this.categorys.remove(entity);
	}

}
