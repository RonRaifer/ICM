package entity;

import java.io.Serializable;

public class RequestHandling implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1379059658818766816L;
	private String idrequest;
	private String idCharge;
	private String executionTime;
	private String currentStage;
	private String status;

	public RequestHandling(String idrequest, String idCharge, String executionTime, String currentStage, String status) {
		this.idrequest = idrequest;
		this.idCharge = idCharge;
		this.executionTime = executionTime;
		this.currentStage = currentStage;
		this.status = status;
	}

	

	public String getIdrequest() {
		return idrequest;
	}

	public String getIdCharge() {
		return idCharge;
	}
	public String getExecutionTime() {
		return executionTime;
	}
	public String getCurrentStage() {
		return currentStage;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
