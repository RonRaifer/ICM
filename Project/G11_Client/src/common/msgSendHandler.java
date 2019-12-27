package common;

import java.io.Serializable;
import java.util.ArrayList;

public class msgSendHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 261834733778535924L;
	private MsgEnum msgEnum;
	private String msgType;
	private ArrayList<Object> msgParams; 

	public msgSendHandler(MsgEnum msgEnum, String msgType, ArrayList<Object> msgParams) {
		this.msgEnum = msgEnum;
		this.msgType = msgType;
		this.msgParams = msgParams;
	}
	
	
	
}
