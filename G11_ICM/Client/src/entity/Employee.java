package entity;

public class Employee extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8670639212770164750L;
	
	private String empRole;
	
	public Employee(String idUser, String firstName, String lastName, String email, String role, String department) {
		super(idUser, firstName, lastName, email, role, department);
		// TODO Auto-generated constructor stub
	}
	public void setEmployeeRole(String empRole) {
		this.empRole = empRole;
	}
	public String getEmploteeRole() {
		return empRole;
	}
	
}
