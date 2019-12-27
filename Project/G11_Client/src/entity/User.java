package entity;

public class User {
	private String idUser;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String department;
	private String password;
	
	public User(String idUser, String firstName, String lastName, String email, String role, String department,
			String password) {
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.department = department;
		this.password = password;
	}

	public String getIdUser() {
		return idUser;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public String getDepartment() {
		return department;
	}

	public String getPassword() {
		return password;
	}
}
