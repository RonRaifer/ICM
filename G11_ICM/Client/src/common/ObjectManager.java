package common;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.*;

public class ObjectManager implements Serializable {

	private static final long serialVersionUID = 261834733778535924L;
	private MsgEnum msgEnum;
	private String errorMsg;

	//Entities
	private User user;
	private Request req;
	private List<Document> listOfFiles;
	private Integer reqIDFromServer;
	private ArrayList<ArrayList<String>> listOfMessages;
	private ResultSet rs;
	
	
	
	

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public ObjectManager( ResultSet rs,MsgEnum msgEnum) {
		
		this.msgEnum = msgEnum;
		this.rs = rs;
	}

	public ObjectManager( Integer reqIDFromServer, MsgEnum msgEnum) {
		
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
	
	public ObjectManager(String errorMsg, MsgEnum msgEnum) { //for error message
		this.errorMsg = errorMsg;
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
	public ObjectManager(ArrayList<ArrayList<String>> listOfMessages, MsgEnum msgEnum) {
		this.msgEnum = msgEnum;
		this.listOfMessages = listOfMessages;
	}
	

	public List<Document> getListOfFiles(){
		return listOfFiles;
	}
	public ArrayList<ArrayList<String>> listOfMessages(){
		return listOfMessages;
	}

	public User getUser() { //get user
		return user;
	}
	public MsgEnum getMsgEnum() { //get MsgEnum
		return msgEnum;
	}
	public String getError() {
		return errorMsg;
	}
	
	public Request getReques() {
		return req;
	}
	
}
