package entity;

import java.io.Serializable;

public class EvaluationReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2814323715285014273L;
	
	private String location;
	private String description;
	private String result;
	private String risk;
	private String time;
	private String idReq;
	
	public EvaluationReport() {
		
	}
	
	public String getIdReq() {
		return idReq;
	}

	public void setIdReq(String idReq) {
		this.idReq = idReq;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	

}
