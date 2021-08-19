package etiyaGame;

public class User {
	
	private String userFirstName;
	private String userLAstName;
	private int userAge;
	
	@Override
	public String toString() {
		return "User [userFirstName=" + userFirstName + ", userLAstName=" + userLAstName + ", userAge=" + userAge + "]";
	}
	User (String userFirstName,String userLAstName, int userAge){
		this.userFirstName = userFirstName;
		this.userLAstName =userLAstName;
		this.userAge = userAge ; 
	}
	public User() {
		
	}
	
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLAstName() {
		return userLAstName;
	}
	public void setUserLAstName(String userLAstName) {
		this.userLAstName = userLAstName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
}
