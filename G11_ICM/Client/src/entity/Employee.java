package entity;

public class Employee extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8670639212770164750L;
	
	private String empRole;
	
	public Employee(String idUser, String firstName, String lastName, String email, String role, String department, String empRole) {
		super(idUser, firstName, lastName, email, role, department);
		this.empRole = empRole;
	}
	public void setEmployeeRole(String empRole) {
		this.empRole = empRole;
	}
	public String getEmploteeRole() {
		return empRole;
	}
	
}
