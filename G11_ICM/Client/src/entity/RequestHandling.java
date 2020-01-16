package entity;

import java.io.Serializable;
/**
 * this class represents request_handling table in the DB
 * it has the current stage details
 * 
 *
 */
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
/**
 * constructor with the following fields
 * @param idrequest
 * @param idCharge
 * @param executionTime
 * @param currentStage
 * @param status
 */
	public RequestHandling(String idrequest, String idCharge, String executionTime, String currentStage, String status) {
		this.idrequest = idrequest;
		this.idCharge = idCharge;
		this.executionTime = executionTime;
		this.currentStage = currentStage;
		this.status = status;
	}

	
/**
 * the id of the request this stage belongs to
 * @return the id of the request this stage belongs to
 */
	public String getIdrequest() {
		return idrequest;
	}
/**
 * the id of the person in charge of this stage 
 * @return the id of the person in charge of this stage
 */
	public String getIdCharge() {
		return idCharge;
	}
	/**
	 * the amount of time this stage should take
	 * @return the amount of time this stage should take
	 */
	public String getExecutionTime() {
		return executionTime;
	}
	/**
	 * the name of the current stage
	 * @return the name of the current stage
	 */
	public String getCurrentStage() {
		return currentStage;
	}
/**
 * the status of the current stage
 * @return the status of the current stage
 */
	public String getStatus() {
		return status;
	}
	/**
	 * set the status of the current stage
	 * @param status - the status of the current stage
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}
