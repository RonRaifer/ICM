package common;

import java.io.Serializable;
import java.util.ArrayList;

import entity.*;

public class ObjectManager implements Serializable {

	private static final long serialVersionUID = 261834733778535924L;
	private MsgEnum msgEnum;

	//Entities
	private User user;
	
	public ObjectManager(User user, MsgEnum msgEnum) { //user login request
		this.user = user;
		this.msgEnum = msgEnum;
	}
	
	
	
}
