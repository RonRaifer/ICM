package common;

import java.io.Serializable;

public enum MsgEnum implements Serializable {
	LOGIN, LOGIN_ERROR,
	LOGOUT, ADD_REQUEST,ADD_FILES,SEND_ID_OF_REQUEST_TO_CLIENT,
	GET_REQUESTS_BY_ID,SEND_RS_NOT_STARTED_TO_CLIENT, SEND_RS_STARTED_TO_CLIENT;
	
}
