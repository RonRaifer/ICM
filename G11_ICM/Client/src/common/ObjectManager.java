package common;

import java.io.Serializable;

import entity.*;

public class ObjectManager implements Serializable {

	private static final long serialVersionUID = 261834733778535924L;
	private MsgEnum msgEnum;
	private String errorMsg;

	//Entities
	private User user;
	
	public ObjectManager(User user, MsgEnum msgEnum) { //user login request
		this.user = user;
		this.msgEnum = msgEnum;
	}
	
	public ObjectManager(String errorMsg, MsgEnum msgEnum) { //for error message
		this.errorMsg = errorMsg;
		this.msgEnum = msgEnum;
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
	
	
	
}
