package models;

public class Admin extends User {
	String pwd;
	String userName;

	public Admin(String firstName, String familyName,  String email, String pwd,
			String userName) {
		super(firstName, familyName, email);
		this.pwd = pwd;
		this.userName = userName;
	}

	public Admin(String firstName, String familyName, String email) {
		super(firstName, familyName,email);
		this.pwd = null;
		this.userName = null;
	}
	public Admin() {}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
}