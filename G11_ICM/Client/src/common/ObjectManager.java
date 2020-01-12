package common;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.*;

public class ObjectManager implements Serializable {

	private static final long serialVersionUID = 261834733778535924L;
	private MsgEnum msgEnum;
	private String msgString;

	//Entities
	private User user;
	private Request req;
	private List<Document> listOfFiles;
	private Integer reqIDFromServer;
	private ResultSet rs;
	private ArrayList<?> array;
	private boolean evFlag;
  private EvaluationReport evReport;

  
  
	public boolean isEvFlag() {
	return evFlag;
}

public void setEvFlag(boolean evFlag) {
	this.evFlag = evFlag;
}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public ObjectManager(ResultSet rs,MsgEnum msgEnum) {
		
		this.msgEnum = msgEnum;
		this.rs = rs;
	}
  public ObjectManager(EvaluationReport evReport, MsgEnum msgEnum) {
		this.msgEnum = msgEnum;
		this.evReport = evReport;
	}
	public ObjectManager(Integer reqIDFromServer, MsgEnum msgEnum) {
		
		this.msgEnum = msgEnum;
		this.reqIDFromServer = reqIDFromServer;
	}

	public Integer getReqIDFromServer() {
		return reqIDFromServer;
	}

	public void setReqIDFromServer(Integer reqIDFromServer) {
		this.reqIDFromServer = reqIDFromServer;
	}

	public ObjectManager(User user, MsgEnum msgEnum) { //user login request
		this.user = user;
		this.msgEnum = msgEnum;
	}
	
	public ObjectManager(String msgString, MsgEnum msgEnum) { //for any string handler
		this.msgString = msgString;
		this.msgEnum = msgEnum;
	}
	
	public ObjectManager(Request req, MsgEnum msgEnum) {
		this.msgEnum = msgEnum;
		this.req = req;
	}
	public ObjectManager(List<Document> listOfFiles, MsgEnum msgEnum) {
		this.msgEnum = msgEnum;
		this.listOfFiles=listOfFiles;
		
	}
	public ObjectManager(ArrayList<?> array, MsgEnum msgEnum) { //for arraylists
		this.msgEnum = msgEnum;
		setArray(array);
	}

	public List<Document> getListOfFiles(){
		return listOfFiles;
	}

	public ArrayList<?> getArray() {
		return array;
	}
	public void setArray(ArrayList<?> array) {
		this.array = array;
	}
	public User getUser() { //get user
		return user;
	}
	public MsgEnum getMsgEnum() { //get MsgEnum
		return msgEnum;
	}
	public String getMsgString() {
		return msgString;
	}
	
	public Request getReques() {
		return req;
	}
  public EvaluationReport getEvReport() {
		return evReport;
	}

	public void setEvReport(EvaluationReport evReport) {
		this.evReport = evReport;
	}

}
