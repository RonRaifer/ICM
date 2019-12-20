package logic.users;

public class User {
	private String user_id;	
	private String Name;
	private String LastName;
	private String Email;
	private String Department;
	
	public User(String user_id, String name, String lastName, String email, String department) {
		this.user_id = user_id;
		Name = name;
		LastName = lastName;
		Email = email;
		Department = department;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getName() {
		return Name;
	}

	public String getLastName() {
		return LastName;
	}

	public String getEmail() {
		return Email;
	}

	public String getDepartment() {
		return Department;
	}
	
}