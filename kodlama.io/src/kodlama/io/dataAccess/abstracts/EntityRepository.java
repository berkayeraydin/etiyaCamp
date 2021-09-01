package kodlama.io.dataAccess.abstracts;

import java.util.List;

public interface EntityRepository<T> {
	
	List<T> getAll();
	void add(T entity);
	void update(T entity);
	void remova(T entity);
}
