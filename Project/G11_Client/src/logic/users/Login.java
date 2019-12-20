package logic.users;

public class Login {
	private String user_id;
	private String Password;
	private String Role; //Worker/Worker with job/Student/Lecturer
	
	public Login(String user_id, String password, String role) {
		this.user_id = user_id;
		Password = password;
		Role = role;
	}
	public String getUser_id() {
		return user_id;
	}
	public String getPassword() {
		return Password;
	}
	public String getRole() {
		return Role;
	}
	
	
}
