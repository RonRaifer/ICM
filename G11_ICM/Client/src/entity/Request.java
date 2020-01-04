package entity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import common.MyFile;

public class Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idReq;
	private String curState;
	private String requestedChange;
	private String purpose;
	private String comments;
	private String system;
	private String opentDate;
	private String idUser;
	private String status;
	
	
	public String getIdReq() {
		return idReq;
	}
	public void setIdReq(String idReq) {
		this.idReq = idReq;
	}
	public String getCurState() {
		return curState;
	}
	public void setCurState(String curState) {
		this.curState = curState;
	}
	public String getRequestedChange() {
		return requestedChange;
	}
	public void setRequestedChange(String requestedChange) {
		this.requestedChange = requestedChange;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getOpentDate() {
		return opentDate;
	}
	public void setOpentDate(String opentDate) {
		this.opentDate = opentDate;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public List<String> getAll(){
		List<String> list = new ArrayList<>();
		list.add(idReq);
		list.add(curState);
		list.add(requestedChange);
		list.add(purpose);
		list.add(comments);
		list.add(system);
		list.add(opentDate);
		list.add(idUser);
		list.add(status);
		
		return list;
	}
	
	

}
