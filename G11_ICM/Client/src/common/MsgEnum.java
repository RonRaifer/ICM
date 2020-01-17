package common;

import java.io.Serializable;

public enum MsgEnum implements Serializable {
	LOGIN, LOGIN_ERROR,
	LOGOUT, ADD_REQUEST,ADD_FILES,SEND_ID_OF_REQUEST_TO_CLIENT,
	GET_REQUESTS_BY_ID_STARTED,GET_REQUESTS_BY_ID_NOSTARTED,
	SEND_RS_NOT_STARTED_TO_CLIENT, SEND_RS_STARTED_TO_CLIENT, 
	VIEW_MESSAGES,SEND_MESSAGES_TO_CLIENT, VIEW_EMPLOYEES,VIEW_PROCESSES,VIEW_ACTIONS,VIEW_PROCESSES_TO_BE_DETERMINED,VIEW_REQUEST,
	ADD_EV_REPORT,CHECK_PRE_EV,SET_PRE_EV_FLAG,CLIENT_EV_REP, GET_EV_REPORT, SET_EV_IN_REVIEW,INFO_REVIEW,MORE_INFO_REVIEW,GET_CHANGE_INFO,
	SET_INFO_EXECUATION, EXECUTION_DONE, VIEW_EMPLOYEES_TO_APPOINT,APPROVE_CHECKING,REJECT_CHECKING, APPOINT_EVALUATOR,
	CHECK_REVIEW_EXIST, SET_REVIEW_EXIST,ADD_REVIEW, REVIEW_LEADER_APPROVE, APPOINT_STAGE_CHARGE, UPDADE_DEPARTMENT, VIEW_EMPLOYEES_WITH_ROLES;
}
