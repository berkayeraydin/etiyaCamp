package kodlama.io.entities.concretes;

public class Category {
	
	private int id;
	private String categoryName;
	private Course course;
	
	public Category() {
		super();
	}
	
	public Category(int id, String categoryName, Course course) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.course = course;
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
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", course=" + course + "]";
	}
	

}
