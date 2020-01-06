package common;

import java.io.Serializable;

public enum MsgEnum implements Serializable {
	LOGIN, LOGIN_ERROR,
	LOGOUT, ADD_REQUEST,ADD_FILES,SEND_ID_OF_REQUEST_TO_CLIENT, VIEW_MESSAGES;
	
}
