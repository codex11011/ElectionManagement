package models;

import java.io.Serializable;

public abstract class User implements Serializable {
	String firstName;
	String familyName;
	String email;

	public User() {

	}

	public User(String firstName, String familyName, String email) {
		super();
		this.firstName = firstName;
		this.familyName = familyName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
